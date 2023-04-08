package com.github.v43d3rm4k4r.besthotels.presentation

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.github.v43d3rm4k4r.besthotels.R
import com.github.v43d3rm4k4r.besthotels.databinding.ActivityMainBinding

/**
 * Необходимо написать приложение, которые загружает с сервера JSON с информацией о нескольких отелях
 * (API – "Список отелей"), парсит его и выводит на экран данные этих отелей. Формат отображения этих
 * данных остается на усмотрение разработчика. Пока идет загрузка данных, пользователь должен об этом
 * знать, соответственно, необходимо отображать activity indicator (не индикатор в статусбаре).
 *
 * Должна присутствовать возможность отсортировать отели по одному из двух параметров: по расстоянию отеля от
 * центра города или по количеству свободных номеров (данные есть в JSON-файле из API). Контрол для выбора
 * типа сортировки остаётся на усмотрение разработчика. Первичная сортировка должна совпадать с выдачей с сервера.
 *
 * Необходимо предусмотреть возможность просмотра подробной информации об отеле на отдельном экране, включая изображение
 * (API "Картинка отеля"). Данные конкретного отеля можно получить по методу "Отель" (при этом ID каждого отеля
 * получается в методе "Список отелей"). Формат отображения данных об отеле остаётся на усмотрение разработчика.
 * Изображение отеля имеет границу по краю шириной в 1px, обязательно надо избежать отображения этой границы в
 * интерфейсе (???).
 *
 * Приложение должно поддерживать систему Android 8.0 и выше (minSdkVersion 26). Ориентации — портретная и ландшафтная.
 * Важно: обязательно соблюдение гайдлайнов и использование стандартных контролов (ActionBar, спиннеры, кнопки).
Код должен быть поддерживаем и переиспользуем.


 TODO: This application using:
- Clean Architecture + MVVM
- Coroutines + Flow
- Navigation Component + SafeArgs
- Modules for Resources & Utils
- Dagger
- Room
- Unit Testing
- Animations
- Network Connectivity Observer
- lottie?


 TODO: Additionally Added ?:
- SearchView

HotelsViewModelUseCases:
TODO: OpenHotelDetailsUseCase
TODO: SortHotelsUseCase (with enum)
TODO: SearchHotelUseCase ?

HotelDetailsViewModelUseCase
TODO: ShowImageDetailsUseCase ?


 Enjoy :)
 */

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}