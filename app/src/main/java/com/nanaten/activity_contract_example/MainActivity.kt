package com.nanaten.activity_contract_example

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    object Contracts: ActivityResultContract<Int, User?>() {
        // Activity起動時の処理
        override fun createIntent(context: Context, input: Int): Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("select_id", input)
            return intent
        }

        // 結果受け取り時の処理
        override fun parseResult(resultCode: Int, intent: Intent?): User? {
            if(resultCode == Activity.RESULT_OK) {
                return intent?.getParcelableExtra("result")
            }
            return null
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Simple StartActivityForResult
        val simpleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Activityから受け取った結果を処理する
            if(result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val user = intent?.getParcelableExtra<User>("result") ?: return@registerForActivityResult
                setResultText(user)
            }
        }

        // Custom StartActivityForResult
        val customLauncher = registerForActivityResult(Contracts) { result ->
            // Activityから受け取った結果を処理する
            result ?: return@registerForActivityResult
            setResultText(result)
        }

        // Permission
        val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if(it) {
                // リクエスト許可時の処理
                Toast.makeText(this, "Permission Accepted.", Toast.LENGTH_SHORT).show()
            }
            else {
                // リクエスト拒否時の処理
                Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show()
            }
        }

        launch_button_left.setOnClickListener {
            // Activityを起動
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("select_id", User.Attribute.ADULT.id)
            simpleLauncher.launch(intent)
        }
        launch_button_right.setOnClickListener {
            // Activityを起動
            customLauncher.launch(User.Attribute.CHILD.id)
        }
        request_button.setOnClickListener {
            // 受け取りたいPermissionを引数に渡す
            requestPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun setResultText(user: User) {
        val attr = User.Attribute.values()[user.attr].value
        text_view.text = ("名前:${user.name}  属性:$attr")
    }
}
