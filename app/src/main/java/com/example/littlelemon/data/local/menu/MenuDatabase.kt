package com.example.littlelemon.data.local.menu

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerialName

private const val DatabaseName = "menu_item_database"

@Entity(tableName = "menu")
data class MenuItemLocal(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

@Dao
interface MenuItemDao {

    @Query("SELECT * from menu")
    fun getAllMenuItems(): Flow<List<MenuItemLocal>>

    @Insert
    suspend fun insertAll(vararg menuItem: MenuItemLocal)

    @Delete
    suspend fun delete(menuItem: MenuItemLocal)

    @Query("SELECT(SELECT COUNT(*) FROM menu) == 0")
    suspend fun isEmpty(): Boolean

}

@Database(entities = [MenuItemLocal::class], version = 1, exportSchema = false)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao

    companion object {
        @Volatile
        private var Instance: MenuDatabase? = null
        fun getDatabase(context: Context): MenuDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MenuDatabase::class.java, DatabaseName)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}