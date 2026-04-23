package com.voyager.airlines.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FlightDao_Impl implements FlightDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Flight> __insertionAdapterOfFlight;

  private final EntityInsertionAdapter<Airline> __insertionAdapterOfAirline;

  private final EntityInsertionAdapter<City> __insertionAdapterOfCity;

  public FlightDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFlight = new EntityInsertionAdapter<Flight>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `flights` (`id`,`airlineId`,`sourceCityId`,`destinationCityId`,`departureTime`,`arrivalTime`,`basePrice`,`isConnecting`,`stopoverCityId`,`availableSeats`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Flight entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getAirlineId());
        statement.bindString(3, entity.getSourceCityId());
        statement.bindString(4, entity.getDestinationCityId());
        statement.bindLong(5, entity.getDepartureTime());
        statement.bindLong(6, entity.getArrivalTime());
        statement.bindDouble(7, entity.getBasePrice());
        final int _tmp = entity.isConnecting() ? 1 : 0;
        statement.bindLong(8, _tmp);
        if (entity.getStopoverCityId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getStopoverCityId());
        }
        statement.bindLong(10, entity.getAvailableSeats());
      }
    };
    this.__insertionAdapterOfAirline = new EntityInsertionAdapter<Airline>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `airlines` (`id`,`name`,`logoUrl`,`isLowCost`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Airline entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getLogoUrl());
        final int _tmp = entity.isLowCost() ? 1 : 0;
        statement.bindLong(4, _tmp);
      }
    };
    this.__insertionAdapterOfCity = new EntityInsertionAdapter<City>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `cities` (`id`,`name`,`country`,`airportCode`,`imageUrl`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final City entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getCountry());
        statement.bindString(4, entity.getAirportCode());
        statement.bindString(5, entity.getImageUrl());
      }
    };
  }

  @Override
  public Object insertFlights(final List<Flight> flights,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFlight.insert(flights);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAirlines(final List<Airline> airlines,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAirline.insert(airlines);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertCities(final List<City> cities,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCity.insert(cities);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Flight>> getAllFlights() {
    final String _sql = "SELECT * FROM flights";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"flights"}, new Callable<List<Flight>>() {
      @Override
      @NonNull
      public List<Flight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAirlineId = CursorUtil.getColumnIndexOrThrow(_cursor, "airlineId");
          final int _cursorIndexOfSourceCityId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceCityId");
          final int _cursorIndexOfDestinationCityId = CursorUtil.getColumnIndexOrThrow(_cursor, "destinationCityId");
          final int _cursorIndexOfDepartureTime = CursorUtil.getColumnIndexOrThrow(_cursor, "departureTime");
          final int _cursorIndexOfArrivalTime = CursorUtil.getColumnIndexOrThrow(_cursor, "arrivalTime");
          final int _cursorIndexOfBasePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "basePrice");
          final int _cursorIndexOfIsConnecting = CursorUtil.getColumnIndexOrThrow(_cursor, "isConnecting");
          final int _cursorIndexOfStopoverCityId = CursorUtil.getColumnIndexOrThrow(_cursor, "stopoverCityId");
          final int _cursorIndexOfAvailableSeats = CursorUtil.getColumnIndexOrThrow(_cursor, "availableSeats");
          final List<Flight> _result = new ArrayList<Flight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Flight _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAirlineId;
            _tmpAirlineId = _cursor.getString(_cursorIndexOfAirlineId);
            final String _tmpSourceCityId;
            _tmpSourceCityId = _cursor.getString(_cursorIndexOfSourceCityId);
            final String _tmpDestinationCityId;
            _tmpDestinationCityId = _cursor.getString(_cursorIndexOfDestinationCityId);
            final long _tmpDepartureTime;
            _tmpDepartureTime = _cursor.getLong(_cursorIndexOfDepartureTime);
            final long _tmpArrivalTime;
            _tmpArrivalTime = _cursor.getLong(_cursorIndexOfArrivalTime);
            final double _tmpBasePrice;
            _tmpBasePrice = _cursor.getDouble(_cursorIndexOfBasePrice);
            final boolean _tmpIsConnecting;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsConnecting);
            _tmpIsConnecting = _tmp != 0;
            final String _tmpStopoverCityId;
            if (_cursor.isNull(_cursorIndexOfStopoverCityId)) {
              _tmpStopoverCityId = null;
            } else {
              _tmpStopoverCityId = _cursor.getString(_cursorIndexOfStopoverCityId);
            }
            final int _tmpAvailableSeats;
            _tmpAvailableSeats = _cursor.getInt(_cursorIndexOfAvailableSeats);
            _item = new Flight(_tmpId,_tmpAirlineId,_tmpSourceCityId,_tmpDestinationCityId,_tmpDepartureTime,_tmpArrivalTime,_tmpBasePrice,_tmpIsConnecting,_tmpStopoverCityId,_tmpAvailableSeats);
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
  public Object getFlightCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM flights";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Flight>> searchFlights(final String sourceId, final String destId) {
    final String _sql = "\n"
            + "        SELECT * FROM flights \n"
            + "        WHERE sourceCityId = ? AND destinationCityId = ?\n"
            + "        ORDER BY basePrice ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, sourceId);
    _argIndex = 2;
    _statement.bindString(_argIndex, destId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"flights"}, new Callable<List<Flight>>() {
      @Override
      @NonNull
      public List<Flight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAirlineId = CursorUtil.getColumnIndexOrThrow(_cursor, "airlineId");
          final int _cursorIndexOfSourceCityId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceCityId");
          final int _cursorIndexOfDestinationCityId = CursorUtil.getColumnIndexOrThrow(_cursor, "destinationCityId");
          final int _cursorIndexOfDepartureTime = CursorUtil.getColumnIndexOrThrow(_cursor, "departureTime");
          final int _cursorIndexOfArrivalTime = CursorUtil.getColumnIndexOrThrow(_cursor, "arrivalTime");
          final int _cursorIndexOfBasePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "basePrice");
          final int _cursorIndexOfIsConnecting = CursorUtil.getColumnIndexOrThrow(_cursor, "isConnecting");
          final int _cursorIndexOfStopoverCityId = CursorUtil.getColumnIndexOrThrow(_cursor, "stopoverCityId");
          final int _cursorIndexOfAvailableSeats = CursorUtil.getColumnIndexOrThrow(_cursor, "availableSeats");
          final List<Flight> _result = new ArrayList<Flight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Flight _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAirlineId;
            _tmpAirlineId = _cursor.getString(_cursorIndexOfAirlineId);
            final String _tmpSourceCityId;
            _tmpSourceCityId = _cursor.getString(_cursorIndexOfSourceCityId);
            final String _tmpDestinationCityId;
            _tmpDestinationCityId = _cursor.getString(_cursorIndexOfDestinationCityId);
            final long _tmpDepartureTime;
            _tmpDepartureTime = _cursor.getLong(_cursorIndexOfDepartureTime);
            final long _tmpArrivalTime;
            _tmpArrivalTime = _cursor.getLong(_cursorIndexOfArrivalTime);
            final double _tmpBasePrice;
            _tmpBasePrice = _cursor.getDouble(_cursorIndexOfBasePrice);
            final boolean _tmpIsConnecting;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsConnecting);
            _tmpIsConnecting = _tmp != 0;
            final String _tmpStopoverCityId;
            if (_cursor.isNull(_cursorIndexOfStopoverCityId)) {
              _tmpStopoverCityId = null;
            } else {
              _tmpStopoverCityId = _cursor.getString(_cursorIndexOfStopoverCityId);
            }
            final int _tmpAvailableSeats;
            _tmpAvailableSeats = _cursor.getInt(_cursorIndexOfAvailableSeats);
            _item = new Flight(_tmpId,_tmpAirlineId,_tmpSourceCityId,_tmpDestinationCityId,_tmpDepartureTime,_tmpArrivalTime,_tmpBasePrice,_tmpIsConnecting,_tmpStopoverCityId,_tmpAvailableSeats);
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
  public Flow<List<Flight>> getCheapestFlights() {
    final String _sql = "\n"
            + "        SELECT * FROM flights \n"
            + "        ORDER BY basePrice ASC LIMIT 10\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"flights"}, new Callable<List<Flight>>() {
      @Override
      @NonNull
      public List<Flight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAirlineId = CursorUtil.getColumnIndexOrThrow(_cursor, "airlineId");
          final int _cursorIndexOfSourceCityId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceCityId");
          final int _cursorIndexOfDestinationCityId = CursorUtil.getColumnIndexOrThrow(_cursor, "destinationCityId");
          final int _cursorIndexOfDepartureTime = CursorUtil.getColumnIndexOrThrow(_cursor, "departureTime");
          final int _cursorIndexOfArrivalTime = CursorUtil.getColumnIndexOrThrow(_cursor, "arrivalTime");
          final int _cursorIndexOfBasePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "basePrice");
          final int _cursorIndexOfIsConnecting = CursorUtil.getColumnIndexOrThrow(_cursor, "isConnecting");
          final int _cursorIndexOfStopoverCityId = CursorUtil.getColumnIndexOrThrow(_cursor, "stopoverCityId");
          final int _cursorIndexOfAvailableSeats = CursorUtil.getColumnIndexOrThrow(_cursor, "availableSeats");
          final List<Flight> _result = new ArrayList<Flight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Flight _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAirlineId;
            _tmpAirlineId = _cursor.getString(_cursorIndexOfAirlineId);
            final String _tmpSourceCityId;
            _tmpSourceCityId = _cursor.getString(_cursorIndexOfSourceCityId);
            final String _tmpDestinationCityId;
            _tmpDestinationCityId = _cursor.getString(_cursorIndexOfDestinationCityId);
            final long _tmpDepartureTime;
            _tmpDepartureTime = _cursor.getLong(_cursorIndexOfDepartureTime);
            final long _tmpArrivalTime;
            _tmpArrivalTime = _cursor.getLong(_cursorIndexOfArrivalTime);
            final double _tmpBasePrice;
            _tmpBasePrice = _cursor.getDouble(_cursorIndexOfBasePrice);
            final boolean _tmpIsConnecting;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsConnecting);
            _tmpIsConnecting = _tmp != 0;
            final String _tmpStopoverCityId;
            if (_cursor.isNull(_cursorIndexOfStopoverCityId)) {
              _tmpStopoverCityId = null;
            } else {
              _tmpStopoverCityId = _cursor.getString(_cursorIndexOfStopoverCityId);
            }
            final int _tmpAvailableSeats;
            _tmpAvailableSeats = _cursor.getInt(_cursorIndexOfAvailableSeats);
            _item = new Flight(_tmpId,_tmpAirlineId,_tmpSourceCityId,_tmpDestinationCityId,_tmpDepartureTime,_tmpArrivalTime,_tmpBasePrice,_tmpIsConnecting,_tmpStopoverCityId,_tmpAvailableSeats);
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
  public Object getAirline(final String id, final Continuation<? super Airline> $completion) {
    final String _sql = "SELECT * FROM airlines WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Airline>() {
      @Override
      @NonNull
      public Airline call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfLogoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "logoUrl");
          final int _cursorIndexOfIsLowCost = CursorUtil.getColumnIndexOrThrow(_cursor, "isLowCost");
          final Airline _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpLogoUrl;
            _tmpLogoUrl = _cursor.getString(_cursorIndexOfLogoUrl);
            final boolean _tmpIsLowCost;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLowCost);
            _tmpIsLowCost = _tmp != 0;
            _result = new Airline(_tmpId,_tmpName,_tmpLogoUrl,_tmpIsLowCost);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getCity(final String id, final Continuation<? super City> $completion) {
    final String _sql = "SELECT * FROM cities WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<City>() {
      @Override
      @NonNull
      public City call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfAirportCode = CursorUtil.getColumnIndexOrThrow(_cursor, "airportCode");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final City _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCountry;
            _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            final String _tmpAirportCode;
            _tmpAirportCode = _cursor.getString(_cursorIndexOfAirportCode);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            _result = new City(_tmpId,_tmpName,_tmpCountry,_tmpAirportCode,_tmpImageUrl);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllCitiesSync(final Continuation<? super List<City>> $completion) {
    final String _sql = "SELECT * FROM cities";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<City>>() {
      @Override
      @NonNull
      public List<City> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfAirportCode = CursorUtil.getColumnIndexOrThrow(_cursor, "airportCode");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final List<City> _result = new ArrayList<City>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final City _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCountry;
            _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            final String _tmpAirportCode;
            _tmpAirportCode = _cursor.getString(_cursorIndexOfAirportCode);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            _item = new City(_tmpId,_tmpName,_tmpCountry,_tmpAirportCode,_tmpImageUrl);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
