package kcl.teamIndexZero.traffic.simulator.data.features;

import kcl.teamIndexZero.traffic.simulator.data.ID;
import kcl.teamIndexZero.traffic.simulator.data.descriptors.JunctionDescription;
import kcl.teamIndexZero.traffic.simulator.data.geo.GeoPoint;
import kcl.teamIndexZero.traffic.simulator.data.geo.GeoPolyline;
import kcl.teamIndexZero.traffic.simulator.data.geo.GeoSegment;
import kcl.teamIndexZero.traffic.simulator.data.links.Link;
import kcl.teamIndexZero.traffic.simulator.exceptions.AlreadyExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Es on 06/03/2016.
 */
public class JunctionTest {
    private Junction j;
    private Road r1;
    private Road r2;
    private Road r3;

    @Before
    public void setUp() throws Exception {
        j = new Junction(new ID("JunctionTest"), true, new GeoPoint(0, 0));
        GeoPolyline mockedPolyLine = mock(GeoPolyline.class);
        r1 = new Road(new ID("Road1"), 1, 1, 12000, mockedPolyLine, "Road 1");
        r2 = new Road(new ID("Road2"), 1, 2, 10000, mockedPolyLine, "Road 2");
        r3 = new Road(new ID("Road3"), 2, 0, 10000, mockedPolyLine, "Road 3");
    }

    @After
    public void tearDown() throws Exception {
        j = null;
        r1 = null;
        r2 = null;
        r3 = null;
    }

    @Test
    public void testAddRoad() throws Exception {
        assertEquals(0, j.getConnectedFeatures().size());
        j.addRoad(r1, JunctionDescription.RoadDirection.INCOMING);
        assertEquals(1, j.getConnectedFeatures().size());
        assertEquals(r1, j.getConnectedFeatures().iterator().next());
    }

    @Test(expected = AlreadyExistsException.class)
    public void testAddRoad_Exception() throws AlreadyExistsException {
        j.addRoad(r1, JunctionDescription.RoadDirection.INCOMING);
        j.addRoad(r1, JunctionDescription.RoadDirection.INCOMING);
    }

    @Test
    public void testComputeAllPaths() throws Exception {
        j.addRoad(r1, JunctionDescription.RoadDirection.INCOMING);
        j.addRoad(r2, JunctionDescription.RoadDirection.OUTGOING);
        j.addRoad(r3, JunctionDescription.RoadDirection.OUTGOING);
        j.computeAllPaths();
        List<Link> list_r1_0 = j.getNextLinks(r1.getForwardSide().getLanes().get(0).getNextLink().getID());
        List<Link> list_r2_0 = j.getNextLinks(r2.getBackwardSide().getLanes().get(0).getNextLink().getID());
        List<Link> list_r2_1 = j.getNextLinks(r2.getBackwardSide().getLanes().get(1).getNextLink().getID());
        assertEquals(3, list_r1_0.size());
        assertEquals(3, list_r2_0.size());
        assertEquals(3, list_r2_1.size());
    }

    @Test
    public void testGetConnectedFeatures() throws Exception {
        assertEquals(0, j.getConnectedFeatures().size());
        j.addRoad(r1, JunctionDescription.RoadDirection.INCOMING);
        assertEquals(1, j.getConnectedFeatures().size());
        j.addRoad(r2, JunctionDescription.RoadDirection.OUTGOING);
        assertEquals(2, j.getConnectedFeatures().size());
        j.addRoad(r3, JunctionDescription.RoadDirection.OUTGOING);
        assertEquals(3, j.getConnectedFeatures().size());
    }

    @Test
    public void testHasTrafficLights() throws Exception {
        assertTrue(j.hasTrafficLights());
        Junction j2 = new Junction(new ID("JunctionTest"), false, new GeoPoint(0, 0));
        assertFalse(j2.hasTrafficLights());
    }

    @Test
    public void testBearingForLanes() throws AlreadyExistsException {
        GeoPolyline poly = new GeoPolyline(Arrays.asList(
                new GeoSegment(
                        new GeoPoint(0, 0),
                        new GeoPoint(1000, 0)
                ),
                new GeoSegment(
                        new GeoPoint(1000, 0),
                        new GeoPoint(1000, 1000)
                )));
        Road road = new Road(new ID("r1"), 1, 1, 10, poly, "Road1");
        Junction j1 = new Junction(new ID("j1"), false, new GeoPoint(0, 0));
        j1.addRoad(road, JunctionDescription.RoadDirection.OUTGOING);

        assertThat(j1.getBearingForLane(road.getForwardSide().getLanes().get(0))).isEqualTo(0);
        assertThat(j1.getBearingForLane(road.getBackwardSide().getLanes().get(0))).isEqualTo(Math.toRadians(180));

        road = new Road(new ID("r1"), 1, 1, 10, poly, "Road1");
        Junction j2 = new Junction(new ID("j1"), false, new GeoPoint(0, 0));
        j2.addRoad(road, JunctionDescription.RoadDirection.INCOMING);
        assertThat(j2.getBearingForLane(road.getForwardSide().getLanes().get(0))).isEqualTo(Math.toRadians(90));
        assertThat(j2.getBearingForLane(road.getBackwardSide().getLanes().get(0))).isEqualTo(Math.toRadians(270));

    }

    @Test
    public void testGetGeoPoint() throws Exception {

    }

    @Test
    public void testAddTrafficGenerator() throws Exception {

    }

    @Test
    public void testGetBearingForLane() throws Exception {

    }

    @Test
    public void testGetNextLinks() throws Exception {

    }

    @Test
    public void testGetRandomLink() throws Exception {

    }

    @Test
    public void testGetInflowLinks() throws Exception {

    }

    @Test
    public void testGetOutflowLinks() throws Exception {

    }

    @Test
    public void testGetInflowCount() throws Exception {

    }

    @Test
    public void testGetOutflowCount() throws Exception {

    }

    @Test
    public void testIncrementUsage() throws Exception {

    }

    @Test
    public void testGetUsage() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void testIsDeadEnd() throws Exception {

    }
}