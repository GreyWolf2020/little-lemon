package com.example.littlelemon.data.local.userorder

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.littlelemon.UserOrderMessage
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

private const val DATASTORE_FILENAME = "userOrderDataStore"
object UserOrderSerializer : Serializer<UserOrderMessage> {
    override val defaultValue: UserOrderMessage
        get() = UserOrderMessage.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserOrderMessage = try {
        UserOrderMessage.parseFrom(input)
    } catch (e: InvalidProtocolBufferException) {
        throw Exception("Cannot read proto", e)
    }

    override suspend fun writeTo(t: UserOrderMessage, output: OutputStream) = t.writeTo(output)

    val Context.userOrderDataStore: DataStore<UserOrderMessage> by dataStore(
        fileName = DATASTORE_FILENAME,
        serializer = UserOrderSerializer
    )
}