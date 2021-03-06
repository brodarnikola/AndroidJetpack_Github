package com.vjezba.androidjetpackgithub

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView
import com.vjezba.androidjetpackgithub.ui.slideshow.SlideshowFragment
import kotlinx.android.synthetic.main.activity_languages.*

class LanguagesActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_languages)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /*nav_view.setNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.nav_home -> {
                    true
                }
            }
            true
        }*/

        val drawerToggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.view_pager_fragment, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setupSpeedDialView()

    }

    private fun setupSpeedDialView() {
        val speedDialView: SpeedDialView = findViewById(R.id.speedDial)
        speedDialView.addActionItem(
            SpeedDialActionItem.Builder(R.id.sd_main_fab, R.drawable.ic_android_24dp)
                .create()
        )

        speedDialView.setOnActionSelectedListener { speedDialActionItem ->
            when (speedDialActionItem.id) {
                R.id.action_dogo1 -> {
                    /*val direction =
                         HomeViewPagerFragmentDirections.actionLanguageDetailFragmentToLanguagesFragment()
                     navController.navigate(direction)*/
                    //navController.navigate(R.id.view_pager_fragment)
                    Toast.makeText(this, "Doggo1 action clicked!", Toast.LENGTH_LONG).show()
                    false // true to keep the Speed Dial open
                }
                R.id.action_dogo2 -> {
                    /* val direction =
                       HomeViewPagerFragmentDirections.actionLanguageDetailFragmentToSlideshowFragment()
                   navController.navigate(direction)*/
                    //navController.navigate(R.id.nav_slideshow)
                    Toast.makeText(this, "Doggo2 action clicked!", Toast.LENGTH_LONG).show()
                    false // true to keep the Speed Dial open
                }
                else -> {
                    false
                }
            }
        }

        speedDialView.inflate(R.menu.menu_speed_dial)

        speedDialView.addActionItem(
            SpeedDialActionItem.Builder(R.id.sd_main_fab, R.drawable.ic_android_24dp)
                .setFabBackgroundColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorAccent,
                        theme
                    )
                )
                .setFabImageTintColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorPrimary,
                        theme
                    )
                )
                .setLabel("AAAAA BBBB")
                .setLabelColor(Color.WHITE)
                .setLabelBackgroundColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorPrimaryDark,
                        theme
                    )
                )
                .setLabelClickable(false)
                .create()
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}