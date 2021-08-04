package br.com.samuelnunes.rickandmortyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.samuelnunes.rickandmortyapp.data.entities.Character

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 04/08/2021 at 14:08
 */
@Database(entities = [Character::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}