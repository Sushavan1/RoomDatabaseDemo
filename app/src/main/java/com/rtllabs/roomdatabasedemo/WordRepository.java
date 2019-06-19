package com.rtllabs.roomdatabasedemo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.rtllabs.roomdatabasedemo.dataBase.Word;
import com.rtllabs.roomdatabasedemo.dataBase.WordDao;
import com.rtllabs.roomdatabasedemo.dataBase.WordRoomDataBase;

import java.util.List;

public class WordRepository {
    private LiveData<List<Word>> mAllWords;
    private WordDao mWordDao;

    public WordRepository(Application application) {
        WordRoomDataBase db = WordRoomDataBase.getDataBase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new InsertAsyncTask(mWordDao).execute(word);
    }

    public void delete(Word word) {
        new DeleteAsyncTask(mWordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        public InsertAsyncTask(WordDao wordDao) {
            mAsyncTaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        public DeleteAsyncTask(WordDao wordDao) {
            mAsyncTaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
