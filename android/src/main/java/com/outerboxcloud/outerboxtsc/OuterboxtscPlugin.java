package com.outerboxcloud.outerboxtsc;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import com.example.tscdll.TscWifiActivity;

/** OuterboxtscPlugin */
public class OuterboxtscPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity

  TscWifiActivity tscEthernetRunner = new TscWifiActivity();


  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "outerboxtsc");
    channel.setMethodCallHandler(new OuterboxtscPlugin());
  }

  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "outerboxtsc");
    channel.setMethodCallHandler(new OuterboxtscPlugin());
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("sticker")) {
      String ipAddress = call.argument("ipAddress");
      String lot = call.argument("lot");
      String product = call.argument("product");
      String number = call.argument("number");
      //String purchaseUom = call.argument("purchaseUom");
      //String uom = call.argument("uom");
      //String uomQty = call.argument("uomQty");
      //String printedName = call.argument("printedName");
     // String done = call.argument("done");
      //String poNum = call.argument("origin");
      //String vendor = call.argument("contact");
      // String expiryDate = call.argument("expiryDate");
      String currentDate = call.argument("currentDate");
     // String location = call.argument("location");
      // String staffId = call.argument("staffId");
     // String productCode = call.argument("productCode");

      //int num = Integer.parseInt(number);
//      String uomString;
//      if(uom == purchaseUom) {
//        uomString = done + " " + uom;
//      } else {
//        uomString = uomQty + " " + printedName + " " + "(" + done + " " + uom + ")";
//      }
//      String poString = poNum;
      //String expString = "EXP: " + expiryDate;

      tscEthernetRunner.openport(ipAddress,9100, 50);
      tscEthernetRunner.setup(61, 38, 4, 1, 0, 3, 0);
      tscEthernetRunner.clearbuffer();
     // tscEthernetRunner.sendcommand("DIRECTION 0\n");
    //  tscEthernetRunner.sendcommand("SET COUNTER @1 1\n");
//      if (num < 10) {
//        tscEthernetRunner.sendcommand("@1 = \"1\"\n");
//      } else if (num < 100) {
//        tscEthernetRunner.sendcommand("@01 = \"1\"\n");
//      }else {
//        tscEthernetRunner.sendcommand("@001 = \"1\"\n");
//      }
      tscEthernetRunner.barcode(16, 0, "128", 46, 2, 0, 2, 2,lot);
      tscEthernetRunner.printerfont(328, 8, "3", 0, 1, 1, "Outerbox");
//      tscEthernetRunner.printerfont(552, 16, "3", 0, 1, 1, staffId);
      tscEthernetRunner.printerfont(14, 80, "2", 0, 1, 1, product);
     // tscEthernetRunner.printerfont(328, 80, "2", 0, 1, 1, vendor);
      //tscEthernetRunner.printerfont(14, 104, "2", 0, 1, 1, uomString);
    //  tscEthernetRunner.printerfont(328, 104, "2", 0, 1, 1, poString);
      //tscEthernetRunner.printerfont(14, 128, "2", 0, 1, 1, expString);
      tscEthernetRunner.printerfont(328, 128, "2", 0, 1, 1, currentDate);
      tscEthernetRunner.sendcommand("TEXT 42,160,\"3\",0,1,1,@1\n");
    //  tscEthernetRunner.printerfont(96, 160, "2", 0, 1, 1, "of");
      tscEthernetRunner.printerfont(128, 160, "3", 0, 1, 1, number);
      //tscEthernetRunner.printerfont(228, 160, "4", 0, 1, 1, "INBOUND");
      //tscEthernetRunner.printerfont(462, 160, "3", 0, 1, 1, location);
    //  tscEthernetRunner.printlabel(num, 1);
      tscEthernetRunner.closeport();
      result.success("Success Printing");
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    //channel.setMethodCallHandler(null);
  }
}
