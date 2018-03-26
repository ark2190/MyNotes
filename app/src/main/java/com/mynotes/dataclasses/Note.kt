package com.mynotes.dataclasses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonIgnoreType
import com.fasterxml.jackson.annotation.JsonInclude
import io.realm.RealmModel
import io.realm.annotations.RealmClass
import java.util.*

/**
 * This object stores all the information related to a note.
 *
 * Created by Anurag on 25-03-2018.
 */
@RealmClass
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
open class Note : RealmModel {
    var id: String = ""
    var body: String? = null
    var timestamp: Date? = null
}