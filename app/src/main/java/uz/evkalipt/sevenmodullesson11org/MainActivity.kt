package uz.evkalipt.sevenmodullesson11org

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import uz.evkalipt.sevenmodullesson11org.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnDataPass {
    lateinit var binding: ActivityMainBinding
    var data1 = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBarMain.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(binding.navView)
        }

        var navController = findNavController(R.id.fragment)
        binding.bottomNav.itemIconTintList = null
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    navController.navigateUp()
                    navController.navigate(R.id.firstFragment)
                    binding.appBarMain.search.visibility = View.INVISIBLE
                }
                R.id.all_valyut -> {
                    navController.navigateUp()
                    navController.navigate(R.id.secondFragment)
                    binding.appBarMain.search.visibility = View.VISIBLE
                }
                R.id.calculator -> {
                    navController.navigateUp()
                    navController.navigate(R.id.thirdFragment)
                    binding.appBarMain.search.visibility = View.INVISIBLE
                }
            }
            true
        }


        binding.appBarMain.search.setOnClickListener {
            var intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.fragment).navigateUp()
    }

    override fun onBackPressed() {

        if (binding.bottomNav.selectedItemId==R.id.calculator ||
            binding.bottomNav.selectedItemId==R.id.all_valyut ||
            binding.bottomNav.selectedItemId==R.id.home){
            super.onBackPressed()
        }

        if (binding.drawerLayout.isOpen) {
            binding.drawerLayout.closeDrawers()
        } else {
            super.onBackPressed()

        }
    }

    override fun onDataPass(data: Int) {
        data1 = data
        if (data1 == 3) {
            binding.bottomNav.selectedItemId = R.id.calculator
        }
    }
}