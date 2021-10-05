package com.mendelu.fbe.bookshelf

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.mendelu.fbe.bookshelf.databinding.ActivityMainBinding
import com.pdftron.pdf.config.ViewerConfig
import com.pdftron.pdf.controls.DocumentActivity

class MainActivity : AppCompatActivity() {
    companion object{
        private const val STORAGE_PERMISSION_CODE = 1
    }
    private lateinit var binding: ActivityMainBinding


    private fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE
                ).toString()
            )
        ) { }

        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE
            ),
            STORAGE_PERMISSION_CODE
        )
    }

    public fun isReadStorageAllowed(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            this, Manifest.permission.READ_EXTERNAL_STORAGE
        )
        return result == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestStoragePermission()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        binding.mainPageFloatingAction.setOnClickListener {
            navController.navigate(R.id.toMainPageFragment)
        }
        binding.addBookFloatingAction.setOnClickListener {
            navController.navigate(R.id.toAddBookFragment)
        }
        binding.bookmarksFloatingAction.setOnClickListener {
            navController.navigate(R.id.toBookMarKFragment)
        }
        binding.settingsFloatingAction.setOnClickListener {
            navController.navigate(R.id.toSettingsFragment)
        }
        binding.shelfFloatingAction.setOnClickListener {
            navController.navigate(R.id.toShelfFragment)
        }
//        binding.readBookFloatingAction.setOnClickListener {
//            navController.navigate(R.id.toReadBookFragment)
//        }

    }
}