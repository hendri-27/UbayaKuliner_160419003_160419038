package com.ubaya.ubayakuliner_160419003_160419038.util

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import androidx.lifecycle.AndroidViewModel
import com.ubaya.ubayakuliner_160419003_160419038.model.*
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
            db.restaurantDao().insert(Restaurant("Bakso Royal, Manyar","Perum Royal Paka, Blok F No. 5, Jl. Royal Paka, Gunung Anyar, Surabaya","0859465554357","/restaurants/bakso-royal.jpg",null))
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
            db.foodDao().insert(Food(2,"Bakso Campur",15000,"/mie-bakso-pakmameng/1563522652899.jpg"))
            db.foodDao().insert(Food(3,"Ayam Geprek",18150,"/gold-chick/1642202177851186762.jpg"))
            db.foodDao().insert(Food(3,"Ayam Geprek Cabe Ijo",25410,"/gold-chick/1606212562833972510.jpg"))
            db.foodDao().insert(Food(4,"Sate Ayam Daging + Kulit 10 Tusuk",18700,"/sate-ayam-pakfarhan/20201013150117.jpg"))
            db.foodDao().insert(Food(4,"Sate Kambing 10 Tusuk",35000,"/sate-ayam-pakfarhan/20210125210513.jpg"))
            db.foodDao().insert(Food(4,"Es Teh Manis",4000,"/sate-ayam-pakfarhan/20201013154155.jpg"))
            db.foodDao().insert(Food(5, "Soto Ayam",18700,"/soto-ayam-ckan/20220117103018.jpg"))
            db.foodDao().insert(Food(5,"Es Jeruk/Hangat",7200,"/soto-ayam-ckan/20220119151703.jpg"))
            db.foodDao().insert(Food(6,"Soto Jeroan",20000,"/soto-ayam-mail/159064681560054.jpg"))
            db.foodDao().insert(Food(6,"Soto Biasa",15000,"/soto-ayam-mail/12550616543660.jpg"))
            db.foodDao().insert(Food(7,"Bakso Kerikil Tahu",24800,"/bakso-royal/20210125214538.jpg"))
            db.foodDao().insert(Food(7, "Bakso Panas (Bakso Urat Pake Nasi)",31500,"/bakso-royal/20220227213820.jpg"))
            db.foodDao().insert(Food(7,"Bakso Campur",24800,"/bakso-royal/20200906054459.jpg"))
            db.foodDao().insert(Food(8,"Kiwi Tea",12000,"/haus!/1645749989158.jpg"))
            db.foodDao().insert(Food(8,"Lechi Tea",12000,"/haus!/1645749997882.jpg"))
            db.foodDao().insert(Food(9, "Mie Kari Special Next Lv",20000,"/lvmieup/20220103175028.jpg"))
            db.foodDao().insert(Food(9, "Mie Goreng Aceh Special Next Lv",20000,"/lvmieup/20220103180839.jpg"))

            db.cartDao().insert(Cart(1,1,2))
            db.cartDao().insert(Cart(1,2,1))
            db.cartDao().insert(Cart(1,3,1))
            db.cartDao().insert(Cart(1,4,1))
            db.cartDao().insert(Cart(1,6,4))
            db.cartDao().insert(Cart(1,7,3))
            db.cartDao().insert(Cart(2,15,1))

            db.transactionDao().insert(Transaction(1,1,"2022-03-15 17:51","Jl. Rungkut Mapan Indah No. 58, Surabaya",1000,3000,65456,69456,"Completed",1.0f,"910856567581643351115"))
            db.transactionDao().insert(Transaction(2,1,"2022-03-16 15:10","Jl. Jemur Sari Indah No. 100, Surabaya",3000,3000,65456,71456,"Completed",2.0f,"812521431513454664"))
            db.transactionDao().insert(Transaction(1,3,"2022-04-01 10:50","Jl. Menur Tengah No. 3, Surabaya",500,3000,25410,28910,"Completed",3.0f,"1523552255224122444"))
            db.transactionDao().insert(Transaction(1,3,"2022-03-02 08:00","Jl. Tunjungan Indah No. IX/2, Surabaya",500,3000,25410,28910,"Completed",null,"5421315466433451"))

            db.detailTransactionDao().insert(*listOf(
                DetailTransaction("910856567581643351115", 1, 1, 32728),
                DetailTransaction("910856567581643351115",3,1,32728)
            ).toTypedArray())
            db.detailTransactionDao().insert(*listOf(
                DetailTransaction("812521431513454664", 1, 1, 32728),
                DetailTransaction("812521431513454664",3,1,32728)
            ).toTypedArray())
            db.detailTransactionDao().insert(*listOf(
                DetailTransaction("1523552255224122444", 11, 1, 25410)
            ).toTypedArray())
            db.detailTransactionDao().insert(*listOf(
                DetailTransaction("5421315466433451", 11, 1, 25410)
            ).toTypedArray())

            db.reviewDao().insert(Review(1,"910856567581643351115",1,3.0f,"Varian terbaru banget dari bakmi gm. Intinya ini pangsit goreng bakmi gm tapi kulitnya aja tanpa daging. Dibikin snack gitu. Terus dikasi bubuk truffle. Rasa pangsitnya emang khas bakmi gm banget. Kesukaan deh. Buat trufflenya oke sih cukup berasa tapi mang saya lebih suka pangsit goreng originalnya yg dicocol saus merahnya. Ini enak tapi ya trufflenya menurut saya tidak sampai oke banget rasanya. Ini harganya 43k. Dapetnya semangkok ga gede2 amat ae mangkoknya.","2021-12-20 14:57"))
            db.reviewDao().insert(Review(1,"812521431513454664",2,5.0f,"bakmi gm sih udah tidak perlu diragukan lagi kelezatannya. siapa sih yang tidak kenal mie ini? selain mie ayam jamurnya yang kenyal, kamu juga harus beli pangsit gorengnya yang kerap menjadi menu favorit banyak orang yang datang :)","2021-03-21 21:01"))
            db.reviewDao().insert(Review(3,"1523552255224122444",1,5.0f,"Jadi karna harus #dirumahaja jadi ya mesen makan lewat online. Mesen ayam geprek dan ayam geprek sambel ijo. Rasanya beuh endus beud enak lah pedesnya nagih gitu loh apalagi yg sambel ijo mantap","2021-10-17 10:40"))
        }
    }
}