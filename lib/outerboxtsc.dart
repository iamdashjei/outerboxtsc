
import 'dart:async';

import 'package:flutter/services.dart';

class Outerboxtsc {
  static const MethodChannel _channel =
      const MethodChannel('outerboxtsc');

  static Future<String> networkPrint({
    String ipAddress,
    String lot,
    String product,
    String number,
    String currentDate,
  }) async {
    final String result = await _channel.invokeMethod('sticker', {
      'ipAddress': ipAddress,
      'lot': lot,
      'product': product,
      'number': number,
      'currentDate': currentDate,
    });
    return result;
  }
}
