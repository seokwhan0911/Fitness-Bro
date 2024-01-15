package FitnessBro.apiPayload.exception.handler;

import FitnessBro.apiPayload.code.BaseErrorCode;
import FitnessBro.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
