package id.dipoengoro.dcontact

import android.app.Application
import id.dipoengoro.dcontact.data.container.AppContainer
import id.dipoengoro.dcontact.data.container.AppDataContainer

class ContactApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}