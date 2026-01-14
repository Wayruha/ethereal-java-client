package trade.wayruha.ethereal.websocket;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.Response;
import trade.wayruha.ethereal.dto.response.OrderFill;
import trade.wayruha.ethereal.dto.response.OrderInfo;
import trade.wayruha.ethereal.dto.response.PageableResponse;
import trade.wayruha.ethereal.dto.wsresponse.WSBookDepthUpdate;

public interface WebSocketCallback<T> {
    TypeReference<WSBookDepthUpdate> ORDER_BOOK_UPDATE_TYPE = new TypeReference<>() {};
    TypeReference<PageableResponse<OrderInfo>> ORDER_INFO_UPDATE_TYPE = new TypeReference<>() {};
    TypeReference<PageableResponse<OrderFill>> ORDER_FILL_UPDATE_TYPE = new TypeReference<>() {};

    /**
     * Be called when the request successful.
     */
    void onResponse(T response);

    TypeReference<T> getType();

    default void onClosed(int code, String reason){

    }

    default void onFailure(Throwable ex, Response response){

    }

    default void onOpen(Response response){

    }
}
