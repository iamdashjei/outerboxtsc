#import "OuterboxtscPlugin.h"
#if __has_include(<outerboxtsc/outerboxtsc-Swift.h>)
#import <outerboxtsc/outerboxtsc-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "outerboxtsc-Swift.h"
#endif

@implementation OuterboxtscPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftOuterboxtscPlugin registerWithRegistrar:registrar];
}
@end
