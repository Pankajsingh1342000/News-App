package com.example.news20

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.AuthFailureError
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NewsItemClicked {


    private lateinit var adapter: NewsListAdapter

    private var country : String = "in"
    private var category : String = "business"
    private var language : String = "en"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //CALLING check_internet() METHOD
        checkInternet()

        // ASSIGNING recyclervie OF RecyclerView TYPE BY ITS ID
        val recyclerview : RecyclerView = findViewById(R.id.recyclerview)

        // RECYCLER VIEW IS OF LINEAR TYPE AND PASS THE INSTANCE OF THIS ACTIVITY
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ASSIGNING adapter OF NewsListAdapter() TYPE AND PASS THE INSTANCE OF THIS ACTIVITY
        adapter = NewsListAdapter(this)

        // ASSIGNING ALL VALUES OF adapter TO recyclerview.adapter
        recyclerview.adapter = adapter

        // ASSIGNING refresh OF SwipeRefreshLayout TYPE BY ITS ID
        val refresh : SwipeRefreshLayout = findViewById(R.id.swiperefresh)

        // ASSIGNING navigation OF NavigationView TYPE BY ITS ID
        val navigation : NavigationView = findViewById(R.id.navmenu)

        // ASSIGNING toggle OF ActionBarDrawerToggle TYPE
        val toggle : ActionBarDrawerToggle

        // ASSIGNING drawerLayout OF DrawerLayout TYPE BY ITS ID
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerlayout)

        // ASSIGNING toolbar OF Toolbar TYPE BY IS ID
        val toolbar : androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)

        // IT WILL SET THE ACTIONBAR OF toolbar TYPE
        setSupportActionBar(toolbar)

        // ASSIGNING ActionBarDrawerToggle TO toggle AND PASS THE INSTANCE OF THIS ACTIVITY, toolbar AND TWO STRING
        toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)

        // IT WILL ADD toggle TO ACTION BAR, ON CLICKING toggle IT WILL OPEN drawerLayout
        drawerLayout.addDrawerListener(toggle)

        //IT WILL SYNC toggle STATE
        toggle.syncState()

        // CALLING fetchData() METHOD
        fetchData(country,category,language,drawerLayout)

        // IT IS LISTENER OF NAVIGATION, IT WILL DO ALLOCATED WORK WHEN MENU IS SELECTED IN NAVIGATION
        navigation.setNavigationItemSelectedListener {

            // WHEN IS LIKE A SWITCH CASE IT WILL DO CERTAIN WORK WHEN MENU IS SELECTED
            when (it.itemId) {

                // THESE ARE THE COUNTRY MENU

                R.id.india -> {
                    country = "in"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "INDIA", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.us -> {
                    country = "us"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "UNITED STATE", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.sg -> {
                    country = "sg"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "SINGAPORE", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.au -> {
                    country = "au"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "AUSTRALIA", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.pk -> {
                    country = "pk"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "PAKISTAN", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                // THESE ARE LANGUAGE VALUE

                R.id.hi -> {
                    language = "hi"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "HINDI", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.en -> {
                    language = "en"
                    fetchData(country, category, language,drawerLayout)
                    Toast.makeText(applicationContext, "ENGLISH", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                // THESE ARE CATEGORY MENU

                R.id.world -> {
                    category = "world"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "WORLD", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.nation -> {
                    category = "nation"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "nation", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.business -> {
                    category = "business"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "BUSINESS", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.breaking_news -> {
                    category = "breaking-news"
                    fetchData(country, category, language,drawerLayout)
                    Toast.makeText(applicationContext, "BREAKING NEWS", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.sports -> {
                    category = "sports"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "SPORTS", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.technology -> {
                    category = "technology"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "TECHNOLOGY", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.science -> {
                    category = "science"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "SCIENCE", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.health -> {
                    category = "health"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "HEALTH", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.general -> {
                    category = "general"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "GENERAL", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.entertainment -> {
                    category = "entertainment"
                    fetchData(country, category, language, drawerLayout)
                    Toast.makeText(applicationContext, "ENTERTAINMENT", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

            }

            true
        }

        // IT WILL CALL refreshdata() METHOD
        refreshdata(refresh, drawerLayout)

    }

    // IT IS METHOD THAT WILL REFRESH THE THE SCREEN WHEN SCREEN IS PULL DOWN
    private fun refreshdata(refresh: SwipeRefreshLayout, drawerLayout : DrawerLayout) {

        // IT IS LISTENER OF SwipeRefreshLayout, IT WILL BE CALLED WHEN SCREEN IS PULL DOWN
        refresh.setOnRefreshListener {
            fetchData(country, category, language, drawerLayout)

            // IT WILL MAKE REVOLVING CIRCLE EFFECT DISAPPEAR WHEN SCREEN GETS REFRESHED
            refresh.isRefreshing = false
        }
    }

    // IT IS METHOD THAT WILL FETCH THE DATA FROM API
    private fun fetchData(country : String, category : String,language : String, drawerLayout: DrawerLayout) {

        // THIS IS A API LINK
        val url = "https://gnews.io/api/v4/top-headlines?token=823e9e91245c3eb9a4120487bb0bd787&lang=$language&country=$country&topic=$category"

        // CREATING OBJECT OF JsonObjectRequest
        // IT WILL REQUEST THE NEWS DATA FROM NEWS API LINK AND CREATE JASON OBJECTS
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,

            // THIS WILL FETCH THE DATA FROM JASON OBJECT
            { response ->

                // IT WILL GET THE articles OBJECT FROM THE JASON OBJECT AND STORE INTO newsJsonArray
                val newsJsonArray = response.getJSONArray("articles")

                // CREATING ARRAYLIST OF News TYPE WHICH IS A DATACLASS
                val newsArray = ArrayList<News>()

                if(newsJsonArray.length() == 0){

                    AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Alert")
                        .setMessage("NO ARTICLE")
                        .setPositiveButton(
                            "Close"
                        ) { _, _ -> }.show()

                    drawerLayout.openDrawer(Gravity.LEFT)
                }

                else{

                    // LOOP FROM 0 UNTIL LENGTH OF newsJsonArray
                    for (i in 0 until newsJsonArray.length()) {

                        val newsJsonObject = newsJsonArray.getJSONObject(i)

                        // IT WILL EXTRACT THE DTA FROM newsJsonObject
                        val news = News(

                            // EXTRACTING TITLE OF NEWS
                            newsJsonObject.getString("title"),

                            // EXTRACTING URL OF NEWS
                            newsJsonObject.getString("url"),

                            // EXTRACTING IMAGE OF NEWS
                            newsJsonObject.getString("image")
                        )

                        // ADDING EXTRACTED DATA INTO newsArray OF TYPE ARRAYLIST (DATA CLASS)
                        newsArray.add(news)
                    }

                }

                // UPDATING EXTRACTED DATA INTO adapter
                adapter.updateNews(newsArray)

            },

            // ANY FAILURE IN API CALL THEN IT WILL WORK
            {
                // SHOW MESSAGE OUT OF REQUEST
                AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Alert")
                    .setMessage("DAILY LIMIT EXHAUSTED\nCOMEBACK TOMORROW")
                    .setPositiveButton(
                        "Close"
                    ) { _, _ -> finish() }.show()

            })

        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    // WHEN ANY NEWS IS SELECTED, IT WILL OPEN THE THE NEWS IN CHROME
    override fun onItemclicked(item: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }

    // IT IS A METHOD TO CHECK THE INTERNET CONNECTION
    private fun checkInternet(){

        // IF INTERNET CONNECTION IS NOT ALLOWED
        if (!isNetworkAvailable) {
            AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Internet Connection Alert")
                .setMessage("Please Check Your Internet Connection")
                .setPositiveButton(
                    "Close"
                ) { _, _ -> finish() }.show()
        }

        // WHEN INTERNET CONNECTION IS ALLOWED
        else if (isNetworkAvailable) {
            Toast.makeText(
                this@MainActivity,
                "Welcome", Toast.LENGTH_LONG
            ).show()
        }
    }

    // IT WILL CHECK WEATHER INTERNET CONNECTION IS DETECTED OR NOT
    private val isNetworkAvailable: Boolean
        get() {
            val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                    }
                }
            }
            return false
        }

}