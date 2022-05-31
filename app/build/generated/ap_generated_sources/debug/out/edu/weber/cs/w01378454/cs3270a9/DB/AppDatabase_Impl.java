package edu.weber.cs.w01378454.cs3270a9.DB;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile CourseDAO _courseDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Course` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `id` TEXT, `name` TEXT, `course_name` TEXT, `course_code` TEXT, `start_at` TEXT, `end_at` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c9291ceb953eb6e0c82b135807ea7065')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Course`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCourse = new HashMap<String, TableInfo.Column>(7);
        _columnsCourse.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("id", new TableInfo.Column("id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("course_name", new TableInfo.Column("course_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("course_code", new TableInfo.Column("course_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("start_at", new TableInfo.Column("start_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("end_at", new TableInfo.Column("end_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCourse = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCourse = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCourse = new TableInfo("Course", _columnsCourse, _foreignKeysCourse, _indicesCourse);
        final TableInfo _existingCourse = TableInfo.read(_db, "Course");
        if (! _infoCourse.equals(_existingCourse)) {
          return new RoomOpenHelper.ValidationResult(false, "Course(edu.weber.cs.w01378454.cs3270a9.DB.Course).\n"
                  + " Expected:\n" + _infoCourse + "\n"
                  + " Found:\n" + _existingCourse);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c9291ceb953eb6e0c82b135807ea7065", "f42533032dd864c32e896380d3f71c24");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Course");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Course`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public CourseDAO CourseDAO() {
    if (_courseDAO != null) {
      return _courseDAO;
    } else {
      synchronized(this) {
        if(_courseDAO == null) {
          _courseDAO = new CourseDAO_Impl(this);
        }
        return _courseDAO;
      }
    }
  }
}
