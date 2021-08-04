package br.com.samuelnunes.rickandmortyapp.di

import android.content.Context
import androidx.room.Room
import br.com.samuelnunes.rickandmortyapp.data.local.AppDatabase
import br.com.samuelnunes.rickandmortyapp.data.remote.CharacterService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 03/08/2021 at 15:09
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)

    @Provides
    @Singleton
    fun database(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "characters.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}