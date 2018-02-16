package connection.chain;

import gui.events.TranscriptEvent;
import gui.playing.DispatcherAdapter;
import responses.Response;
import responses.ResponseHeader;

public class TranscriptLink extends Chain {
    @Override
    public void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter) {
        if (response.getHeader() == ResponseHeader.TRANSCRIPT) {
            dispatcherAdapter.fireEvent(new TranscriptEvent(response.getTranscriptDTOs()));
        } else {
            nextInChain.analyzeResponse(response, dispatcherAdapter);
        }
    }
}
