# Activity-Contract-Example

From `androidx.fragment:fragment-ktx:1.3.0-alpha04`, the following method is `deprecated`: `androidx.fragment:fragment-ktx:1.3.0-alpha04`.
- startActivityForResult
- onActivityResult
- requestPermissions
- onRequestPermissionsResult

[official source](https://developer.android.com/jetpack/androidx/releases/fragment#1.3.0-alpha04)

The announcement says to use `registerForActivityResult` of `ActivityResultContract` instead.

[Registering a callback for an Activity Result](https://developer.android.com/training/basics/intents/result)

Then, I summarized the usage of `registerForActivityResult`.