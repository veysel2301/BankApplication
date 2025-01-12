#BankApplication

Money Transfer App Control
-------------------
Tags: chrome,Money_Transfer_App_Control
* Web sayfası "https://catchylabs-webclient.testinium.com/" adresinden açılır.
* Kullanici adi ve şifre ile giris yapilir.
* Account name,account type,creation time ve amount alanları kontrol edilir.


Edit Account
-------------------
Tags: mobile,android,Edit_Acoount
* Web sayfası "https://catchylabs-webclient.testinium.com/" adresinden açılır.
* Kullanici adi ve şifre ile giris yapilir.
* Edit Account butonuna tiklanir.
* Account name degistirilir.
* Account name önceki haline döndürülür.


Add Money
-------------------
Tags: firefox,Add_Money
* Web sayfası "https://catchylabs-webclient.testinium.com/" adresinden açılır.
* Kullanici adi ve şifre ile giris yapilir.
* "amountTextControl" elementindeki degeri hafizaya kaydet
* Add Money butonuna tiklanir.
* Card number alanina 16 haneli kart numarasi girilir.
* Card Holder alanina kart sahibi ismi yazilir.
* Expiry Date alanina kartin son kullanma tarihi yazilir.
* CVV alanina kartin guvenlik kodu yazilir.
* Amount alanina eklenmek istenen miktar yazilir.
* Add butonuna tiklanir.
* Eklenen miktarin ne kadar oldugu hesaplanir.

Add Money Amount Control
-------------------
Tags: firefox,Add_Money_Amount_Control
* Web sayfası "https://catchylabs-webclient.testinium.com/" adresinden açılır.
* Kullanici adi ve şifre ile giris yapilir.
* "amountTextControl" elementindeki degeri hafizaya kaydet
* Add Money butonuna tiklanir.
* Card number alanina 16 haneli kart numarasi girilir.
* Card Holder alanina kart sahibi ismi yazilir.
* Expiry Date alanina kartin son kullanma tarihi yazilir.
* CVV alanina kartin guvenlik kodu yazilir.
* Amount alanina eklenmek istenen miktar "0" yazilir.
* Add butonuna tiklanir.
* Amount alanindaki miktar kontrol edilir.

Transfer Money
-------------------
Tags: firefox,Transfer_Money
* Web sayfası "https://catchylabs-webclient.testinium.com/" adresinden açılır.
* Kullanici adi ve şifre ile giris yapilir.
* Transfer Money butonuna tiklanir.
* Transfer money sayfasinin acildigi kontrol edilir.
*  Sender acccount secimi yapilir.
* Amount alanina transfer edilmek istenilen miktar yazilir.
* Send butonuna tiklanir ve transfer isleminin yapildigi görülür.
* Transfer edilen miktarin ne kadar oldugu hesaplanir.

Transfer Money Receive Account Negative
-------------------
Tags: firefox,Transfer_Money_Receive_Account_Negative
* Web sayfası "https://catchylabs-webclient.testinium.com/" adresinden açılır.
* Kullanici adi ve şifre ile giris yapilir.
* Transfer Money butonuna tiklanir.
* Transfer money sayfasinin acildigi kontrol edilir.
* Sender acccount secimi yapilir.
* Recieve acccount secimi yapilir.
* Amount alanina transfer edilmek istenilen miktar yazilir.
* Send butonuna tiklanir ve transfer isleminin yapildigi görülür.
* Transfer edilen miktarin ne kadar oldugu hesaplanir.

Add Money Warning Messages
----------------------
Tags:edge,Add_Money_Warning_Messages

* Web sayfası "https://catchylabs-webclient.testinium.com/" adresinden açılır.
* Kullanici adi ve şifre ile giris yapilir.
* Add Money butonuna tiklanir.
* Card Number alanı bos birakilarak zorunlu uyarisi görülür.
* Card Holder alanı bos birakilarak zorunlu uyarisi görülür.
* Expiry Date alanı bos birakilarak zorunlu uyarisi görülür.
* Cvv alanı bos birakilarak zorunlu uyarisi görülür.
* Amount alanı bos birakilarak zorunlu uyarisi görülür.
* Card Number alanina eksik veri girilerek uyarı aldığı görüntülenir.
* Card Holder alanina eksik veri girilerek uyarı aldığı görüntülenir.
* Expiry Date alanina eksik veri girilerek uyarı aldığı görüntülenir.
* Cvv alanina eksik veri girilerek uyarı aldığı görüntülenir.

Add Money Warning Messages And Blocker
----------------------
Tags:edge,Add_Money_Warning_Messages_And_Blocker

* Web sayfası "https://catchylabs-webclient.testinium.com/" adresinden açılır.
* Kullanici adi ve şifre ile giris yapilir.
* Add Money butonuna tiklanir.
* Card Number alanina fazla veri girilerek uyarı aldığı görüntülenir.
* Card Holder alanina eksik veri girilerek uyarı aldığı görüntülenir.
* Expiry Date alanina eksik veri girilerek uyarı aldığı görüntülenir.
* Cvv alanina eksik veri girilerek uyarı aldığı görüntülenir.

