package com.mynotes.core.api

import android.annotation.SuppressLint
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.mynotes.dataclasses.Note
import okhttp3.*
import okio.Buffer
import java.io.IOException
import java.util.*
import javax.inject.Inject


/**
 * Created by Anurag on 25-03-2018.
 */
class DummyResponseInterceptor @Inject constructor(private val objectMapper: ObjectMapper) : Interceptor {
    companion object {
        const val TAG = "DummyResponseInterceptor"
    }

    private val notes: MutableList<Note> = arrayListOf()

    @SuppressLint("LongLogTag")
    override fun intercept(chain: Interceptor.Chain?): Response {
        val uri = chain?.request()?.url()?.toString()
        val method = uri?.substring(uri.lastIndexOf('/') + 1, uri.length)

        val responseMap: MutableMap<String, Any> = mutableMapOf()
        when (method) {
            NotesApi.ADD_NOTE -> {
                val bodyString = bodyToString(chain.request()?.body())
                val note = objectMapper.readValue<Note>(bodyString)
                note.id = UUID.randomUUID().toString()
                note.timestamp = Date()
                notes.add(0, note)

                responseMap["note"] = note
            }
            NotesApi.VIEW_NOTES -> {
                responseMap["notes"] = notes
            }
        }

        val responseString = objectMapper.writeValueAsString(responseMap)

        return Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain?.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
    }

    private fun bodyToString(request: RequestBody?): String {
        try {
            val buffer = Buffer()
            if (request != null)
                request.writeTo(buffer)
            else
                return ""
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "Failed to convert"
        }
    }
}