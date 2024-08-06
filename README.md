# seyirTurk-Decompile

Bu depo, seyirturk.net uygulamasından decompile edilerek çıkartılmış Java sınıfları ve scriptlerden oluşmaktadır.

Amacımız, yeniden kullanılabilir bileşenler sunmak ve bunların başka projelere entegre edilmesini veya öğrenme kaynağı olarak kullanılmasını sağlamaktır.

## Proje Yapısı
### Server-Side Render:
#### [parser.js](/parser.js)
Bu JavaScript dosyası, belirli web sayfalarını server-side render (sunucu taraflı işleme) kullanarak ayrıştırmak için kullanılır. Bu sayede veriler sunucu tarafında işlenir ve son kullanıcıya doğrudan sunulacak hale getirilir.

### Client-Side Render:
#### Java Sınıfları
Uygulama içinde client-side render (istemci taraflı işleme) işlemleri gerçekleştiren bileşenlerdir. Bu sınıflar, platformlardan içerik kazıma ve bu içerikleri istemci üzerinde işleyerek görüntüleme fonksiyonlarını içerir.