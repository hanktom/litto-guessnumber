package com.tom.numberguess

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.tom.numberguess.databinding.ActivityMainBinding

//Controller
class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.simpleName
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    //Contract
    val goResult = registerForActivityResult(StartActivityForResult()) { result ->
        Log.d(TAG, "goResult callback: ${result.data?.getIntExtra("ABC", -1)}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val viewModel : GuessViewModel by viewModels()
        viewModel.min.observe(this, Observer {
            binding.contentMain.min.text = it.toString()
        })
        viewModel.max.observe(this, Observer {
            binding.contentMain.max.text = it.toString()
        })
        viewModel.bingo.observe(this, Observer {
            if (it) {
                AlertDialog.Builder(this)
                    .setTitle("Game result")
                    .setMessage("You got it!")
                    .setPositiveButton("OK", null)
                    .show()
            }
        })
        binding.contentMain.guess.setOnClickListener {
            val num = binding.contentMain.number.text.toString().toInt()
            viewModel.guess(num)
//            viewModel.add()
        }
        binding.fab.setOnClickListener { view ->
            val intent = Intent(this, ResultActivity::class.java).apply {
                intent.putExtras(Bundle().apply {
                    putInt("TIMES", 3)
                    putString("NAME", "Hank")
                })
            }
            goResult.launch(intent)
//            startActivityForResult(intent, 35)
//            startActivity(intent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 35) {
            if (resultCode == RESULT_OK) {

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                Log.d(TAG, "onOptionsItemSelected: ")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }
}