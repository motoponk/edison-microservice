package de.otto.edison.status.domain;

import org.testng.annotations.Test;

import java.util.Collections;

import static de.otto.edison.status.domain.ApplicationInfo.applicationInfo;
import static de.otto.edison.status.domain.ApplicationStatus.applicationStatus;
import static de.otto.edison.status.domain.Status.ERROR;
import static de.otto.edison.status.domain.Status.OK;
import static de.otto.edison.status.domain.Status.WARNING;
import static de.otto.edison.status.domain.StatusDetail.statusDetail;
import static de.otto.edison.status.domain.VersionInfo.versionInfo;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public class ApplicationStatusTest {

    @Test
    public void shouldHaveStatusOkIfDetailsAreOk() {
        // given
        ApplicationStatus applicationStatus = applicationStatus(mock(ApplicationInfo.class), mock(SystemInfo.class), mock(VersionInfo.class), singletonList(
                statusDetail("bar", OK, "a message"))
        );
        // then
        assertThat(applicationStatus.getStatus(), is(OK));
    }

    @Test
    public void shouldHaveStatusWarningIfDetailsContainWarnings() {
        // given
        ApplicationStatus applicationStatus = applicationStatus(mock(ApplicationInfo.class), mock(SystemInfo.class), mock(VersionInfo.class), asList(
                statusDetail("bar", OK, "a message"),
                statusDetail("foobar", WARNING, "another message")
        ));
        // then
        assertThat(applicationStatus.getStatus(), is(WARNING));
    }

    @Test
    public void shouldHaveStatusErrorIfDetailsContainWarnings() {
        // given
        ApplicationStatus applicationStatus = applicationStatus(mock(ApplicationInfo.class), mock(SystemInfo.class), mock(VersionInfo.class), asList(
                statusDetail("bar", OK, "a message"),
                statusDetail("foobar", ERROR, "another message"),
                statusDetail("foobar", WARNING, "yet another message")
        ));
        // then
        assertThat(applicationStatus.getStatus(), is(ERROR));
    }

}