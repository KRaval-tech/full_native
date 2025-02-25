// import 'package:flutter/material.dart';
// import 'package:google_mobile_ads/google_mobile_ads.dart';
//
// void main(){
//   WidgetsFlutterBinding.ensureInitialized();
//   MobileAds.instance.initialize();
//   runApp(const MyApp());
// }
//
// class MyApp extends StatelessWidget {
//   const MyApp({super.key});
//
//   @override
//   Widget build(BuildContext context) {
//     return MaterialApp(
//       debugShowCheckedModeBanner: false,
//       home: FullScreenNativeAd(),
//     );
//   }
// }
//
// class FullScreenNativeAd extends StatefulWidget {
//   const FullScreenNativeAd({super.key});
//
//   @override
//   State<FullScreenNativeAd> createState() => _FullScreenNativeAdState();
// }
//
// class _FullScreenNativeAdState extends State<FullScreenNativeAd> {
//
//   NativeAd? _nativeAd;
//   bool _isAdLoaded = false;
//
//   @override
//   void initState() {
//     // TODO: implement initState
//     super.initState();
//     _loadAd();
//   }
//
//   void _loadAd() {
//     _nativeAd = NativeAd(
//         adUnitId: "ca-app-pub-3940256099942544/1044960115",
//         factoryId: "full_native",
//         request: AdRequest(),
//         listener: NativeAdListener(
//           onAdLoaded: (Ad ad) {
//             setState(() {
//               _isAdLoaded = true;
//             });
//           },
//           onAdFailedToLoad: (Ad ad,LoadAdError error){
//             print("Ad Failed to Load : ${error.message}");
//             ad.dispose();
//           }
//         ),
//     )..load();
//   }
//
//   @override
//   void dispose() {
//     // TODO: implement dispose
//     super.dispose();
//     _nativeAd?.dispose();
//   }
//
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       body: _isAdLoaded
//       ? AdWidget(ad: _nativeAd!)
//       :Center(child: Text("Ad is Loading.....",style: TextStyle(fontSize: 20),),),
//     );
//   }
// }

import 'package:flutter/material.dart';
import 'package:google_mobile_ads/google_mobile_ads.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  MobileAds.instance.initialize();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: HomeScreen(),
    );
  }
}

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Full-Screen Native Ad")),
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const FullScreenNativeAd()),
            );
          },
          child: const Text("Show Full-Screen Ad"),
        ),
      ),
    );
  }
}

class FullScreenNativeAd extends StatefulWidget {
  const FullScreenNativeAd({super.key});

  @override
  State<FullScreenNativeAd> createState() => _FullScreenNativeAdState();
}

class _FullScreenNativeAdState extends State<FullScreenNativeAd> {
  NativeAd? _nativeAd;
  bool _isAdLoaded = false;

  @override
  void initState() {
    super.initState();
    _loadAd();
  }

  void _loadAd() {
    _nativeAd = NativeAd(
      //adUnitId: "ca-app-pub-3940256099942544/2247696110",
      adUnitId:"ca-app-pub-3940256099942544/1044960115",   /// for Video native ad
      factoryId: "full_native",
      request: const AdRequest(),
      listener: NativeAdListener(
        onAdLoaded: (Ad ad) {
          setState(() {
            _isAdLoaded = true;
          });
        },
        onAdFailedToLoad: (Ad ad, LoadAdError error) {
          print("Ad Failed to Load: ${error.message}");
          ad.dispose();
          Navigator.pop(context); // Close the screen if ad fails
        },
      ),
    )..load();
  }

  @override
  void dispose() {
    _nativeAd?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Dismissible(
        key: const Key("nativeAd"),
        direction: DismissDirection.horizontal,
        onDismissed: (direction) {
          Navigator.pop(context);
        },
        child: Stack(
          children: [
            Container(
              color: Colors.black,
              child: Center(
                child: _isAdLoaded
                    ? AdWidget(ad: _nativeAd!)
                    : const Text(
                  "Ad is Loading...",
                  style: TextStyle(fontSize: 20, color: Colors.white),
                ),
              ),
            ),
            // Positioned(
            //   top: 20,
            //   right: 20,
            //   child: IconButton(
            //     icon: const Icon(Icons.close, color: Colors.white, size: 20),
            //     onPressed: () {
            //       Navigator.pop(context);
            //     },
            //   ),
            // ),
          ],
        ),
      ),
    );
  }
}
