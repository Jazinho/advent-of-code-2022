package com.jpalucki;

public class InputProvider {

  public static String getInput() {
    return INPUT_test;
  }

  private static final String INPUT_test = "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB\n" +
    "Valve BB has flow rate=13; tunnels lead to valves CC, AA\n" +
    "Valve CC has flow rate=2; tunnels lead to valves DD, BB\n" +
    "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE\n" +
    "Valve EE has flow rate=3; tunnels lead to valves FF, DD\n" +
    "Valve FF has flow rate=0; tunnels lead to valves EE, GG\n" +
    "Valve GG has flow rate=0; tunnels lead to valves FF, HH\n" +
    "Valve HH has flow rate=22; tunnel leads to valve GG\n" +
    "Valve II has flow rate=0; tunnels lead to valves AA, JJ\n" +
    "Valve JJ has flow rate=21; tunnel leads to valve II";

  private static final String INPUT = "Valve SZ has flow rate=0; tunnels lead to valves GQ, YZ\n" +
    "Valve SP has flow rate=0; tunnels lead to valves LJ, AA\n" +
    "Valve LQ has flow rate=0; tunnels lead to valves EY, JT\n" +
    "Valve AT has flow rate=17; tunnels lead to valves DX, BU, NE, BR, TD\n" +
    "Valve IR has flow rate=0; tunnels lead to valves XN, UI\n" +
    "Valve CF has flow rate=0; tunnels lead to valves XN, BR\n" +
    "Valve TE has flow rate=0; tunnels lead to valves YA, RY\n" +
    "Valve GQ has flow rate=22; tunnels lead to valves SZ, AQ, OW, XJ\n" +
    "Valve DX has flow rate=0; tunnels lead to valves HI, AT\n" +
    "Valve AQ has flow rate=0; tunnels lead to valves AZ, GQ\n" +
    "Valve NE has flow rate=0; tunnels lead to valves AT, IA\n" +
    "Valve OC has flow rate=4; tunnels lead to valves PE, QV, QI, LJ, WX\n" +
    "Valve JO has flow rate=0; tunnels lead to valves AA, UI\n" +
    "Valve BR has flow rate=0; tunnels lead to valves CF, AT\n" +
    "Valve ZW has flow rate=0; tunnels lead to valves JH, EY\n" +
    "Valve TD has flow rate=0; tunnels lead to valves AT, WX\n" +
    "Valve BU has flow rate=0; tunnels lead to valves AT, ES\n" +
    "Valve QI has flow rate=0; tunnels lead to valves OC, XN\n" +
    "Valve PE has flow rate=0; tunnels lead to valves CI, OC\n" +
    "Valve WX has flow rate=0; tunnels lead to valves TD, OC\n" +
    "Valve IA has flow rate=0; tunnels lead to valves UI, NE\n" +
    "Valve TR has flow rate=18; tunnel leads to valve HI\n" +
    "Valve JK has flow rate=0; tunnels lead to valves QV, UI\n" +
    "Valve UB has flow rate=0; tunnels lead to valves OM, AA\n" +
    "Valve KW has flow rate=0; tunnels lead to valves YL, MD\n" +
    "Valve AL has flow rate=0; tunnels lead to valves ZL, WZ\n" +
    "Valve VK has flow rate=11; tunnels lead to valves OM, ZL, CI, VA, XJ\n" +
    "Valve FF has flow rate=0; tunnels lead to valves VD, AA\n" +
    "Valve MD has flow rate=0; tunnels lead to valves KW, YA\n" +
    "Valve VA has flow rate=0; tunnels lead to valves AZ, VK\n" +
    "Valve CI has flow rate=0; tunnels lead to valves VK, PE\n" +
    "Valve LJ has flow rate=0; tunnels lead to valves SP, OC\n" +
    "Valve YL has flow rate=23; tunnels lead to valves OW, KW\n" +
    "Valve JH has flow rate=0; tunnels lead to valves RK, ZW\n" +
    "Valve ES has flow rate=13; tunnel leads to valve BU\n" +
    "Valve OM has flow rate=0; tunnels lead to valves UB, VK\n" +
    "Valve QV has flow rate=0; tunnels lead to valves OC, JK\n" +
    "Valve XN has flow rate=7; tunnels lead to valves QI, VD, IR, CF, OG\n" +
    "Valve EY has flow rate=10; tunnels lead to valves ZW, LQ, XC, RC\n" +
    "Valve XJ has flow rate=0; tunnels lead to valves GQ, VK\n" +
    "Valve HI has flow rate=0; tunnels lead to valves DX, TR\n" +
    "Valve VD has flow rate=0; tunnels lead to valves FF, XN\n" +
    "Valve RY has flow rate=0; tunnels lead to valves AZ, TE\n" +
    "Valve YZ has flow rate=0; tunnels lead to valves SZ, YA\n" +
    "Valve YA has flow rate=12; tunnels lead to valves YZ, MD, TE\n" +
    "Valve AZ has flow rate=14; tunnels lead to valves AQ, RC, RY, VA\n" +
    "Valve ZL has flow rate=0; tunnels lead to valves AL, VK\n" +
    "Valve UE has flow rate=0; tunnels lead to valves RK, UI\n" +
    "Valve WZ has flow rate=25; tunnel leads to valve AL\n" +
    "Valve EB has flow rate=0; tunnels lead to valves AA, XC\n" +
    "Valve UI has flow rate=8; tunnels lead to valves UE, JK, IR, JO, IA\n" +
    "Valve AA has flow rate=0; tunnels lead to valves UB, JO, FF, EB, SP\n" +
    "Valve OG has flow rate=0; tunnels lead to valves XN, DF\n" +
    "Valve RC has flow rate=0; tunnels lead to valves AZ, EY\n" +
    "Valve JT has flow rate=21; tunnel leads to valve LQ\n" +
    "Valve DF has flow rate=0; tunnels lead to valves OG, RK\n" +
    "Valve RK has flow rate=9; tunnels lead to valves DF, JH, UE\n" +
    "Valve OW has flow rate=0; tunnels lead to valves YL, GQ\n" +
    "Valve XC has flow rate=0; tunnels lead to valves EY, EB";
}
