
import 'dart:async';

import 'package:flutter/services.dart';

class Outerboxtsc {
  static const MethodChannel _channel =
      const MethodChannel('outerboxtsc');

  static Future<String> inbound({
    String ipAddress,
    String lot,
    String product,
    String number,
    String currentDate,
  }) async {
    final String result = await _channel.invokeMethod('inbound', {
      'ipAddress': ipAddress,
      'lot': lot,
      'product': product,
      'number': number,
      'currentDate': currentDate,
    });
    return result;
  }
}
