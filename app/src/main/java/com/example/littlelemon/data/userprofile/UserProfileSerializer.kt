package com.example.littlelemon.data.userprofile

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.littlelemon.UserProfileMessage
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

private const val DATASTORE_FILENAME = "userProfileDataStore"

object UserProfileSerializer : Serializer<UserProfileMessage> {
    override val defaultValue: UserProfileMessage
        get() = UserProfileMessage.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserProfileMessage {
        try {
            return UserProfileMessage.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw Exception("Cannot read proto", e)
        }
    }

    override suspend fun writeTo(t: UserProfileMessage, output: OutputStream) = t.writeTo(output)

    val Context.userProfileDataStore: DataStore<UserProfileMessage> by dataStore(
        fileName = DATASTORE_FILENAME,
        serializer = UserProfileSerializer
    )

}