# BankApplication Projesi

Bu proje, banka uygulamaları için geliştirilmiş bir yazılım çözümüdür. Esnek, ölçeklenebilir ve güvenli bir yapı sunarak bankacılık işlemlerinin yönetilmesini sağlar. Uygulama, kullanıcı dostu bir arayüz ve güçlü bir backend altyapısı ile web ve mobil platformlarda kullanılabilir.

## İçindekiler
- [Özellikler](#özellikler)
- [Kurulum](#kurulum)
- [Kullanım](#kullanım)
- [Bağımlılıklar](#bağımlılıklar)
- [Test Otomasyonu](#test-otomasyonu)
- [Spec ve Tag Esneklikleri](#spec-ve-tag-esneklikleri)
- [Çapraz Platform Desteği](#çapraz-platform-desteği)
- [Test Raporu Görüntüleme](#test-raporu-görüntüleme)
- [Katkıda Bulunma](#katkıda-bulunma)
- [Lisans](#lisans)
- [İletişim](#iletişim)

## Özellikler
- **Hesap Yönetimi**: Kullanıcıların hesaplarını ekleyip, güncelleyebilmesi, para transferi yapabilmesi.
- **İşlem Geçmişi**: Yapılan tüm işlemlerin geçmişini görüntüleme.
- **Banka Kartı Yönetimi**: Banka kartı ekleme ve yönetme işlemleri.
- **Güvenlik**: Kullanıcı doğrulama, şifreleme ve güvenli işlem yapabilme.
- **Test Otomasyonu**: Testlerin otomatikleştirilmesi için kapsamlı bir test altyapısı.

## Kurulum
### Depoyu Klonlayın
Öncelikle projeyi GitHub'dan klonlayın:
```bash
git clone https://github.com/veysel2301/BankApplication.git
```

### Proje Dizini
Proje dizinine gidin:
```bash
cd BankApplication
```

### Bağımlılıkları Yükleyin
Maven kullanarak bağımlılıkları yükleyin:
```bash
mvn clean install
```

## Kullanım
### Uygulamayı Başlatın
Uygulamayı başlatmak için aşağıdaki komutu kullanın:
```bash
mvn clean test
```

## Bağımlılıklar

Proje aşağıdaki bağımlılıkları kullanmaktadır:

 - **JUnit 4.13.2**

- **Selenium 4.8.3**

- **Gauge 0.11.1**

- **AssertJ 3.25.3**

- **Log4j 1.2.17**

- **WebDriverManager 5.9.2**

- **AspectJ 1.9.20.1**

Bağımlılıkları `pom.xml` dosyasından kontrol edebilirsiniz.

## Test Otomasyonu
Bu projede test otomasyonu için **Selenium** kullanılmaktadır. Ayrıca, **Gauge** ile kullanıcı senaryoları oluşturulmuş ve testler modüler bir yapıya kavuşturulmuştur.

### Test Çalıştırma
Testleri çalıştırmak için aşağıdaki komutu kullanabilirsiniz:
```bash
mvn test
```

### Taglı Testlerin Çalıştırılması
Testler üzerinde tag kullanarak belirli bir test grubunu çalıştırabilirsiniz. Tag kullanımı sayesinde, sadece belirli senaryoları çalıştırmak mümkündür. Örnek olarak, `@SmokeTest` etiketi ile smoke testlerinizi çalıştırabilirsiniz:
```bash
mvn test -Dgroups=Test1
```

**Tag kullanımı** şu şekilde yapılandırılabilir:
```gauge
# Giriş Senaryosu
Tags : chrome, Test1
Senaryo: Başarılı kullanıcı girişi
  Adım 1: Giriş sayfasına git
  Adım 2: Geçerli kullanıcı bilgilerini gir
  Adım 3: Giriş yap butonuna tıkla

Tags:chrome, Test2
Senaryo: Kredi başvurusunda bulunma
  Adım 1: Kredi başvuru sayfasına git
  Adım 2: Başvuru formunu doldur
  Adım 3: Başvuruyu gönder
```

### Tarayıcı Tabanlı Testler İçin Tag Kullanımı
Projenizde tarayıcı tabanlı testleri çalıştırmak için belirli tag'ler kullanabilirsiniz. Örneğin, testlerinizi farklı tarayıcılarla çalıştırmak için tag'lar kullanmak mümkündür:

- **Chrome İçin Test Başlatma**:
  ```bash
  Tags:chrome
  ```

- **Firefox İçin Test Başlatma**:
  ```bash
  Tags:firefox
  ```

- **Tag'lerin kullanım örneği**:
  ```gauge
  # Chrome Tarayıcı ile Test Senaryosu
  Tags:chrome
  Senaryo: Kullanıcı girişi başarılı olmalı
 
 
**Tag Yapısı:**
- `@chrome` tag'ı ile Chrome tarayıcısında test çalıştırılacaktır.
- `@firefox` tag'ı ile Firefox tarayıcısında test çalıştırılacaktır.

Bu şekilde testlerinizi sadece belirli tarayıcılar ile çalıştırarak çapraz tarayıcı testi gerçekleştirebilirsiniz.

## Spec ve Tag Esneklikleri
Projede **Gauge** kullanarak test senaryoları `.spec` dosyalarında tanımlanır ve bu dosyalar esnek bir yapıya sahiptir. Test adımlarını taglarla etiketlemek, testlerin yönetilmesini ve çalıştırılmasını kolaylaştırır.

Örneğin, **SmokeTest** etiketi ile sadece temel işlevsellik testleri çalıştırılabilir.

## Çapraz Platform Desteği
Proje, **cross-platform** uyumluluğu ile dikkat çekmektedir. Hem Windows, hem macOS hem de Linux işletim sistemlerinde sorunsuz bir şekilde çalışabilir. Maven ve diğer araçlar platform bağımsızdır, bu da farklı ortamlarda aynı sonucu elde etmenizi sağlar.

## Test Raporu Görüntüleme
Testler çalıştırıldıktan sonra **Gauge** ile HTML raporları oluşturulabilir. Raporu görüntülemek için şu komutu kullanarak raporları başlatabilirsiniz:

```bash
start reports\html-report\index.html
```

## Katkıda Bulunma
Bu projeye katkıda bulunmak için aşağıdaki adımları izleyebilirsiniz:

1. Projeyi Forklayın.
2. Yeni bir dal (branch) oluşturun:
   ```bash
   git checkout -b yeni-ozellik
   ```
3. Yapmak istediğiniz değişiklikleri gerçekleştirin ve commit edin:
   ```bash
   git commit -m 'Yeni özellik eklendi'
   ```
4. Değişikliklerinizi remote repository'ye gönderin:
   ```bash
   git push origin yeni-ozellik
   ```
5. Pull request oluşturun.

## Lisans
Bu proje **MIT Lisansı** ile lisanslanmıştır.

## İletişim
Herhangi bir sorunuz, öneriniz veya katkınız için [veysel2301](https://github.com/veysel2301) ile iletişime geçebilirsiniz.
