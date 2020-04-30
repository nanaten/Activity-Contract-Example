package com.nanaten.activity_contract_example

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val selectedId = intent.getIntExtra("select_id", 0)
        // 受け取ったselect_idで押したボタンを判別する
        val resId = User.Attribute.values()[selectedId].res
        which_group.check(resId)

        ok_button.setOnClickListener {
            // 入力されたテキストを元のActivityに渡す
            val user = User(edit_text.text.toString(), selectedId)
            intent.putExtra("result", user)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
