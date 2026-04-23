package com.voyager.airlines.data;

import android.database.Cursor;
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
public final class BookingDao_Impl implements BookingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Booking> __insertionAdapterOfBooking;

  public BookingDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBooking = new EntityInsertionAdapter<Booking>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `bookings` (`id`,`flightId`,`userId`,`passengerCount`,`totalFare`,`bookingTime`,`seatNumbers`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Booking entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getFlightId());
        statement.bindString(3, entity.getUserId());
        statement.bindLong(4, entity.getPassengerCount());
        statement.bindDouble(5, entity.getTotalFare());
        statement.bindLong(6, entity.getBookingTime());
        statement.bindString(7, entity.getSeatNumbers());
      }
    };
  }

  @Override
  public Object insertBooking(final Booking booking, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBooking.insert(booking);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Booking>> getBookings() {
    final String _sql = "SELECT * FROM bookings ORDER BY bookingTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bookings"}, new Callable<List<Booking>>() {
      @Override
      @NonNull
      public List<Booking> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFlightId = CursorUtil.getColumnIndexOrThrow(_cursor, "flightId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfPassengerCount = CursorUtil.getColumnIndexOrThrow(_cursor, "passengerCount");
          final int _cursorIndexOfTotalFare = CursorUtil.getColumnIndexOrThrow(_cursor, "totalFare");
          final int _cursorIndexOfBookingTime = CursorUtil.getColumnIndexOrThrow(_cursor, "bookingTime");
          final int _cursorIndexOfSeatNumbers = CursorUtil.getColumnIndexOrThrow(_cursor, "seatNumbers");
          final List<Booking> _result = new ArrayList<Booking>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Booking _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFlightId;
            _tmpFlightId = _cursor.getString(_cursorIndexOfFlightId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final int _tmpPassengerCount;
            _tmpPassengerCount = _cursor.getInt(_cursorIndexOfPassengerCount);
            final double _tmpTotalFare;
            _tmpTotalFare = _cursor.getDouble(_cursorIndexOfTotalFare);
            final long _tmpBookingTime;
            _tmpBookingTime = _cursor.getLong(_cursorIndexOfBookingTime);
            final String _tmpSeatNumbers;
            _tmpSeatNumbers = _cursor.getString(_cursorIndexOfSeatNumbers);
            _item = new Booking(_tmpId,_tmpFlightId,_tmpUserId,_tmpPassengerCount,_tmpTotalFare,_tmpBookingTime,_tmpSeatNumbers);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
