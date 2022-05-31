package edu.weber.cs.w01378454.cs3270a9.DB;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CourseDAO_Impl implements CourseDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Course> __insertionAdapterOfCourse;

  private final EntityDeletionOrUpdateAdapter<Course> __deletionAdapterOfCourse;

  private final EntityDeletionOrUpdateAdapter<Course> __updateAdapterOfCourse;

  public CourseDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCourse = new EntityInsertionAdapter<Course>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Course` (`_id`,`id`,`name`,`course_name`,`course_code`,`start_at`,`end_at`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Course value) {
        stmt.bindLong(1, value.get_id());
        if (value.getId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getCourse_name() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCourse_name());
        }
        if (value.getCourse_code() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCourse_code());
        }
        if (value.getStart_at() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getStart_at());
        }
        if (value.getEnd_at() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getEnd_at());
        }
      }
    };
    this.__deletionAdapterOfCourse = new EntityDeletionOrUpdateAdapter<Course>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Course` WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Course value) {
        stmt.bindLong(1, value.get_id());
      }
    };
    this.__updateAdapterOfCourse = new EntityDeletionOrUpdateAdapter<Course>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Course` SET `_id` = ?,`id` = ?,`name` = ?,`course_name` = ?,`course_code` = ?,`start_at` = ?,`end_at` = ? WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Course value) {
        stmt.bindLong(1, value.get_id());
        if (value.getId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getCourse_name() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCourse_name());
        }
        if (value.getCourse_code() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCourse_code());
        }
        if (value.getStart_at() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getStart_at());
        }
        if (value.getEnd_at() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getEnd_at());
        }
        stmt.bindLong(8, value.get_id());
      }
    };
  }

  @Override
  public void AddACourse(final Course courses) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCourse.insert(courses);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeleteSelectedCourse(final Course courses) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCourse.handle(courses);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void EditASelectedCourse(final Course courses) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCourse.handle(courses);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Course>> ListAllCourses() {
    final String _sql = "Select * from Course";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Course"}, false, new Callable<List<Course>>() {
      @Override
      public List<Course> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCourseName = CursorUtil.getColumnIndexOrThrow(_cursor, "course_name");
          final int _cursorIndexOfCourseCode = CursorUtil.getColumnIndexOrThrow(_cursor, "course_code");
          final int _cursorIndexOfStartAt = CursorUtil.getColumnIndexOrThrow(_cursor, "start_at");
          final int _cursorIndexOfEndAt = CursorUtil.getColumnIndexOrThrow(_cursor, "end_at");
          final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Course _item;
            final int _tmp_id;
            _tmp_id = _cursor.getInt(_cursorIndexOfId);
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId_1);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCourse_name;
            _tmpCourse_name = _cursor.getString(_cursorIndexOfCourseName);
            final String _tmpCourse_code;
            _tmpCourse_code = _cursor.getString(_cursorIndexOfCourseCode);
            final String _tmpStart_at;
            _tmpStart_at = _cursor.getString(_cursorIndexOfStartAt);
            final String _tmpEnd_at;
            _tmpEnd_at = _cursor.getString(_cursorIndexOfEndAt);
            _item = new Course(_tmp_id,_tmpId,_tmpName,_tmpCourse_name,_tmpCourse_code,_tmpStart_at,_tmpEnd_at);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Course getCourseByID(final String id) {
    final String _sql = "Select * from Course WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
      final int _cursorIndexOfId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfCourseName = CursorUtil.getColumnIndexOrThrow(_cursor, "course_name");
      final int _cursorIndexOfCourseCode = CursorUtil.getColumnIndexOrThrow(_cursor, "course_code");
      final int _cursorIndexOfStartAt = CursorUtil.getColumnIndexOrThrow(_cursor, "start_at");
      final int _cursorIndexOfEndAt = CursorUtil.getColumnIndexOrThrow(_cursor, "end_at");
      final Course _result;
      if(_cursor.moveToFirst()) {
        final int _tmp_id;
        _tmp_id = _cursor.getInt(_cursorIndexOfId);
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId_1);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpCourse_name;
        _tmpCourse_name = _cursor.getString(_cursorIndexOfCourseName);
        final String _tmpCourse_code;
        _tmpCourse_code = _cursor.getString(_cursorIndexOfCourseCode);
        final String _tmpStart_at;
        _tmpStart_at = _cursor.getString(_cursorIndexOfStartAt);
        final String _tmpEnd_at;
        _tmpEnd_at = _cursor.getString(_cursorIndexOfEndAt);
        _result = new Course(_tmp_id,_tmpId,_tmpName,_tmpCourse_name,_tmpCourse_code,_tmpStart_at,_tmpEnd_at);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
