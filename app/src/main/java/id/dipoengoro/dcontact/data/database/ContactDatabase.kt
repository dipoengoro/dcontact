package id.dipoengoro.dcontact.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.dipoengoro.dcontact.data.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var Instance: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase =
            Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ContactDatabase::class.java, "contacts")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
    }
}