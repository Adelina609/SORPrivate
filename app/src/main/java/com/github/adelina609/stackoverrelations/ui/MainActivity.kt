package com.github.adelina609.stackoverrelations.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.adelina609.stackoverrelations.App
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.di.event.component.DaggerQuestionComponent
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = SupportAppNavigator(this, R.id.main_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerQuestionComponent.builder()
            .appComponent(App.getAppComponents())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar as Toolbar?)

//        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                // handle desired action here
//                // One possibility of action is to replace the contents above the nav bar
//                // return true if you want the item to be displayed as the selected item
//                return true;
//            }
//        });
        navigator.applyCommands(arrayOf<Command>(Replace(Screens.ListScreen())))
        bottom_navigation.setOnNavigationItemReselectedListener(BottomNavigationView.OnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.menu_notifications_item -> navigator.applyCommands(arrayOf<Command>(Replace(Screens.ListScreen())))
                R.id.menu_feed_item -> println("Invalid number")
                R.id.menu_profile_item -> navigator.applyCommands(arrayOf<Command>(Replace(Screens.NewQuestionScreen())))
            }
        })
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }


//    override fun onBackPressed() {
//        val fragment =
//            this.supportFragmentManager.findFragmentById(R.id.main_container)
//        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
//            super.onBackPressed()
//        }
//    }
}
