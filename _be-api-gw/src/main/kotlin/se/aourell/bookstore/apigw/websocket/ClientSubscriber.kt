package se.aourell.bookstore.apigw.websocket

import java.util.*
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

interface ClientSubscriber {

    fun update(evt: Any, json: String)

    fun update(evt: Any) {
        val evtName = evt::class.simpleName
        val payload = evt::class.memberProperties.joinToString(separator = ", ") { serializeToJson(it, evt) }
        val json = "{ \"evt\": \"$evtName\", $payload }"
        update(evt, json)
    }

    private fun<T> serializeToJson(prop: KProperty1<T, *>, evt: Any): String {
        val value = prop.getter.call(evt)
        return "\"${prop.name}\": " + if (isSerializedAsString(value)) "\"$value\"" else "$value"
    }

    private fun isSerializedAsString(obj: Any?) =
            when (obj) {
                is String -> true
                is UUID -> true
                else -> false
            }
}
