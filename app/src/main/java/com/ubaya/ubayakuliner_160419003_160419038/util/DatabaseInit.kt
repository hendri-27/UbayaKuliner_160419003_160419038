package com.ubaya.ubayakuliner_160419003_160419038.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ubaya.ubayakuliner_160419003_160419038.model.Food
import com.ubaya.ubayakuliner_160419003_160419038.model.Restaurant
import com.ubaya.ubayakuliner_160419003_160419038.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DatabaseInit(application: Application)  : AndroidViewModel(application), CoroutineScope {
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetch() {
        launch { // Menjalankannya di thread coroutine
            val db = buildDb(getApplication())
            db.userDao().insert(User("rendy","Rendy Simangsih","Male","20-10-1995","082635512635","rendy.simangsih@gmail.com","rendy123","https://randomuser.me/api/portraits/men/3.jpg"))
            db.userDao().insert(User("rudy","Rudy Tabuty","Male","01-01-2000","0811354234478","rudy100@gmail.com","rudy123","https://randomuser.me/api/portraits/men/42.jpg"))
            db.userDao().insert(User("may","May Stefany","Female","21-07-2002","0857837957999","may.may@gmail.com","may123","https://randomuser.me/api/portraits/women/44.jpg"))

            db.restaurantDao().insert(Restaurant("Bakmi GM","Mall Galaxy 3, Lantai 4, Unit 4-344 & 4-345, Jl. Ir Dr Haji Soekarno 178, Mulyorejo, Surabaya","082737776422","/restaurants/bakmi-gm.jpg",4.0f))
            db.restaurantDao().insert(Restaurant("Mie Ayam & Bakso Solo Pak Mameng, Tenggilis Mejoyo","Jl. Kendangsari, Gang Lebar No. 80, Tenggilis Mejoyo, Surabaya","08125633525","/restaurants/mie-bakso-pakmameng.jpg",null))
            db.restaurantDao().insert(Restaurant("Ayam Geprek Gold Chick, Mulyosari","Jl. Mulyosari No. 118, Mulyosari, Mulyorejo, Surabaya","085433887262","/restaurants/gold-chick.jpg",5.0f))
            db.restaurantDao().insert(Restaurant("Sate Ayam Pak Farhan","Gunung Anyar Tengah Gang 8 No 42, Surabaya","0822563566231","/restaurants/sate-ayam-pakfarhan.jpg",null))
            db.restaurantDao().insert(Restaurant("Soto Ayam C.Kan Tenggilis","Jln. Tenggilis Raya Rungkut Mejoyo No.169","0815298566325","/restaurants/soto-ayam-ckan.jpg",null))
            db.restaurantDao().insert(Restaurant("Soto Ayam Mail, Tenggilis Mejoyo","Jl. Kendangsari No. 23, Tenggilis Mejoyo, Surabaya","08264531897555","/restaurants/soto-ayam-mail.jpg",null))
            db.restaurantDao().insert(Restaurant("Soto Ayam Mail, Tenggilis Mejoyo","Perum Royal Paka, Blok F No. 5, Jl. Royal Paka, Gunung Anyar, Surabaya","0859465554357","/restaurants/bakso-royal.jpg",null))
            db.restaurantDao().insert(Restaurant("Haus!, Rungkut Madya","Jl. Rungkut Madya No. 81, Rungkut, Surabaya","0812564585232","/restaurants/haus!.jpg",null))
            db.restaurantDao().insert(Restaurant("LvMieUp, Bendul Merisi Jaya 6 No 5","Jl. Bendul Merisi Jaya 6 No 5, Bendulmerisi, Surabaya","08356426519564","/restaurants/lvmieup.jpg",null))

            db.foodDao().insert(Food(1,"Yi Fu Mie Ni Ayam Cah Cabai",32728,"/bakmi-gm/1399370381.jpg"))
            db.foodDao().insert(Food(1,"Bakmi Special GM",30910,"/bakmi-gm/1373339502.jpg"))
            db.foodDao().insert(Food(1,"Bakmi Pangsit Goreng",32728,"/bakmi-gm/1373340128.jpg"))
            db.foodDao().insert(Food(1,"Bakmi Ayam Cah Jamur",40000,"/bakmi-gm/1373340170.jpg"))
            db.foodDao().insert(Food(1,"Bakmi Goreng",44546,"/bakmi-gm/1373340879.jpg"))
            db.foodDao().insert(Food(1, "Bakmi Brokoli Sapi Lada Hitam",46364,"/bakmi-gm/1373340706.jpg"))
            db.foodDao().insert(Food(1,"Bakmi Doank",19546,"/bakmi-gm/1373341066.jpg"))
            db.foodDao().insert(Food(2,"Mie Ayam Bakso",16000,"/mie-bakso-pakmameng/15226535235.jpg"))
        }
    }
}