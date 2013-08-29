package specs.rthk.handlers;

import com.hoffenkloffen.radio.rthk.handlers.EpisodeHandler;
import com.hoffenkloffen.radio.utils.IDownloader;
import com.hoffenkloffen.radio.utils.ILogFacade;
import org.junit.Test;
import org.mockito.Mockito;
import specs.BaseSpec;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EpisodeHandlerSpec extends BaseSpec {
    protected EpisodeHandler handler;

    private ILogFacade log;
    private IDownloader downloader;

    protected void given() {
        log = Mockito.mock(ILogFacade.class);
        downloader = Mockito.mock(IDownloader.class);

        handler = new EpisodeHandler(log, downloader);
    }

    @Test
    public void should_invalidate_null_uri() {
        boolean result = handler.isValid(null);

        assertThat(result, is(false));
    }

    @Test
    public void should_invalidate_unknown_uri() {
        boolean result = handler.isValid("http://unknown.com/episode.asx");

        assertThat(result, is(false));
    }

    @Test
    public void should_accept_file_uri() {
        boolean result = handler.isValid("http://www.rthk.org.hk/asx/rthk/radio2/siksifung/20130818.asx");

        assertThat(result, is(true));
    }
}