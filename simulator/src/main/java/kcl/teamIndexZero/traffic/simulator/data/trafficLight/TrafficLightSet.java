package kcl.teamIndexZero.traffic.simulator.data.trafficLight;

import kcl.teamIndexZero.traffic.log.Logger;
import kcl.teamIndexZero.traffic.log.Logger_Interface;
import kcl.teamIndexZero.traffic.simulator.data.trafficLight.TrafficLight;
import kcl.teamIndexZero.traffic.simulator.data.trafficLight.TrafficLightInSet;
import kcl.teamIndexZero.traffic.simulator.ISimulationAware;
import kcl.teamIndexZero.traffic.simulator.data.ID;
import kcl.teamIndexZero.traffic.simulator.data.SimulationTick;
import kcl.teamIndexZero.traffic.simulator.data.trafficLight.TrafficLightState;

import java.util.List;
import java.util.*;

/**
 * Traffic Light in a synchronous set
 */

public class TrafficLightSet implements ISimulationAware {
    private ID id;
    public List<TrafficLightInSet> InteriorListA;
    public List<TrafficLightInSet> InteriorListB;
    private static Logger_Interface LOG = Logger.getLoggerInstance(TrafficLightSet.class.getSimpleName());
    public enum  TrafficLightSetGroup{
        GROUPA,
        GROUPB
    }
    /**
     * Constructor
     *
     * @param id TrafficLightSet ID
     */
    public TrafficLightSet(ID id) {
        this.id=id;
        this.InteriorListA =new ArrayList<TrafficLightInSet>();
        this.InteriorListB =new ArrayList<TrafficLightInSet>();
    }

    /**
     * Adds traffic lights to the List of traffic lights within one junction
     *
     * @param trafficLightInSet object to be added to the list
     */
    public void addTrafficlight(TrafficLightInSet trafficLightInSet, TrafficLightSetGroup group ){


        if (group == TrafficLightSetGroup.GROUPA){

                if (!(trafficLightInSet == null)) {
                    LOG.log("Traffic Lights type is the first type");
                    //TrafficLightSetList.add(trafficLightInSet);
                    InteriorListA.add(trafficLightInSet);

                    LOG.log("Added the following traffic lights: ", trafficLightInSet.getID(), " to the set: ", this.id);
                } else {
                    LOG.log_Error("Error while adding to TrafficLightLnSet to the set");
                }

        }

        if (group == TrafficLightSetGroup.GROUPB) {
            if (trafficLightInSet != null) {
                LOG.log("Traffic Lights type is the second type");
                //TrafficLightSetList.add(trafficLightInSet);
                InteriorListB.add(trafficLightInSet);

                LOG.log("Added the following traffic lights: ", trafficLightInSet.getID(), " to the set: ", this.id);

                    /* *********
                    * changing initial states in the second group from GREEN to RED
                    ********** */
                for (TrafficLightInSet tf : InteriorListB) {
                    tf.currentState = TrafficLightState.RED;
                }

                    /* *********
                    * end of changing initial states in the group B
                    ********** */

            } else {
                LOG.log_Error("Error while adding to TrafficLightLnSet to the set");
            }
        }
     }


    /**
     * Returns a groupA from a list of traffic lights within one junction
     *
     * @param id of the junction, to which list is assigned
     */
    public List<TrafficLightInSet> getSetGroupA(ID id) {return this.InteriorListA;}

    /**
     * Returns the ID of the TrafficLightSet
     */
    public ID getID() { return this.id;}

    @Override
    public void tick(SimulationTick tick) {

    }
}
