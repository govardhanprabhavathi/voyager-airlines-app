package com.voyager.airlines.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VoyagerDatabase_Impl extends VoyagerDatabase {
  private volatile FlightDao _flightDao;

  private volatile BookingDao _bookingDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `flights` (`id` TEXT NOT NULL, `airlineId` TEXT NOT NULL, `sourceCityId` TEXT NOT NULL, `destinationCityId` TEXT NOT NULL, `departureTime` INTEGER NOT NULL, `arrivalTime` INTEGER NOT NULL, `basePrice` REAL NOT NULL, `isConnecting` INTEGER NOT NULL, `stopoverCityId` TEXT, `availableSeats` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `airlines` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `logoUrl` TEXT NOT NULL, `isLowCost` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `cities` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `country` TEXT NOT NULL, `airportCode` TEXT NOT NULL, `imageUrl` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `bookings` (`id` TEXT NOT NULL, `flightId` TEXT NOT NULL, `userId` TEXT NOT NULL, `passengerCount` INTEGER NOT NULL, `totalFare` REAL NOT NULL, `bookingTime` INTEGER NOT NULL, `seatNumbers` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'efcca39dbdfb57b82ce5198039a68bb4')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `flights`");
        db.execSQL("DROP TABLE IF EXISTS `airlines`");
        db.execSQL("DROP TABLE IF EXISTS `cities`");
        db.execSQL("DROP TABLE IF EXISTS `bookings`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsFlights = new HashMap<String, TableInfo.Column>(10);
        _columnsFlights.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlights.put("airlineId", new TableInfo.Column("airlineId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlights.put("sourceCityId", new TableInfo.Column("sourceCityId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlights.put("destinationCityId", new TableInfo.Column("destinationCityId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlights.put("departureTime", new TableInfo.Column("departureTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlights.put("arrivalTime", new TableInfo.Column("arrivalTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlights.put("basePrice", new TableInfo.Column("basePrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlights.put("isConnecting", new TableInfo.Column("isConnecting", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlights.put("stopoverCityId", new TableInfo.Column("stopoverCityId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlights.put("availableSeats", new TableInfo.Column("availableSeats", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFlights = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFlights = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFlights = new TableInfo("flights", _columnsFlights, _foreignKeysFlights, _indicesFlights);
        final TableInfo _existingFlights = TableInfo.read(db, "flights");
        if (!_infoFlights.equals(_existingFlights)) {
          return new RoomOpenHelper.ValidationResult(false, "flights(com.voyager.airlines.data.Flight).\n"
                  + " Expected:\n" + _infoFlights + "\n"
                  + " Found:\n" + _existingFlights);
        }
        final HashMap<String, TableInfo.Column> _columnsAirlines = new HashMap<String, TableInfo.Column>(4);
        _columnsAirlines.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAirlines.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAirlines.put("logoUrl", new TableInfo.Column("logoUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAirlines.put("isLowCost", new TableInfo.Column("isLowCost", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAirlines = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAirlines = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAirlines = new TableInfo("airlines", _columnsAirlines, _foreignKeysAirlines, _indicesAirlines);
        final TableInfo _existingAirlines = TableInfo.read(db, "airlines");
        if (!_infoAirlines.equals(_existingAirlines)) {
          return new RoomOpenHelper.ValidationResult(false, "airlines(com.voyager.airlines.data.Airline).\n"
                  + " Expected:\n" + _infoAirlines + "\n"
                  + " Found:\n" + _existingAirlines);
        }
        final HashMap<String, TableInfo.Column> _columnsCities = new HashMap<String, TableInfo.Column>(5);
        _columnsCities.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCities.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCities.put("country", new TableInfo.Column("country", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCities.put("airportCode", new TableInfo.Column("airportCode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCities.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCities = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCities = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCities = new TableInfo("cities", _columnsCities, _foreignKeysCities, _indicesCities);
        final TableInfo _existingCities = TableInfo.read(db, "cities");
        if (!_infoCities.equals(_existingCities)) {
          return new RoomOpenHelper.ValidationResult(false, "cities(com.voyager.airlines.data.City).\n"
                  + " Expected:\n" + _infoCities + "\n"
                  + " Found:\n" + _existingCities);
        }
        final HashMap<String, TableInfo.Column> _columnsBookings = new HashMap<String, TableInfo.Column>(7);
        _columnsBookings.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookings.put("flightId", new TableInfo.Column("flightId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookings.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookings.put("passengerCount", new TableInfo.Column("passengerCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookings.put("totalFare", new TableInfo.Column("totalFare", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookings.put("bookingTime", new TableInfo.Column("bookingTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookings.put("seatNumbers", new TableInfo.Column("seatNumbers", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBookings = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBookings = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBookings = new TableInfo("bookings", _columnsBookings, _foreignKeysBookings, _indicesBookings);
        final TableInfo _existingBookings = TableInfo.read(db, "bookings");
        if (!_infoBookings.equals(_existingBookings)) {
          return new RoomOpenHelper.ValidationResult(false, "bookings(com.voyager.airlines.data.Booking).\n"
                  + " Expected:\n" + _infoBookings + "\n"
                  + " Found:\n" + _existingBookings);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "efcca39dbdfb57b82ce5198039a68bb4", "272f22d7e72965fbceed3981c6b5d3c0");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "flights","airlines","cities","bookings");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `flights`");
      _db.execSQL("DELETE FROM `airlines`");
      _db.execSQL("DELETE FROM `cities`");
      _db.execSQL("DELETE FROM `bookings`");
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
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(FlightDao.class, FlightDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BookingDao.class, BookingDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public FlightDao flightDao() {
    if (_flightDao != null) {
      return _flightDao;
    } else {
      synchronized(this) {
        if(_flightDao == null) {
          _flightDao = new FlightDao_Impl(this);
        }
        return _flightDao;
      }
    }
  }

  @Override
  public BookingDao bookingDao() {
    if (_bookingDao != null) {
      return _bookingDao;
    } else {
      synchronized(this) {
        if(_bookingDao == null) {
          _bookingDao = new BookingDao_Impl(this);
        }
        return _bookingDao;
      }
    }
  }
}
