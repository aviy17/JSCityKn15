package com.building.grl51l5st14;
import java.util.ArrayList;
import java.util.List;

import eu.printingin3d.javascad.models.Abstract3dModel;
import eu.printingin3d.javascad.models.Sphere;
import eu.printingin3d.javascad.coords.Coords3d;
import eu.printingin3d.javascad.coords.Dims3d;
import eu.printingin3d.javascad.models.*;
import eu.printingin3d.javascad.tranzitions.Difference;
import eu.printingin3d.javascad.tranzitions.Union;

class Building extends Union {
    private static final int myCoords1[] = {-133, -67, 0, 67, 133};
    private static final int myCoords2[] = {-80, -20, 40};
    private static int myCoords3 = -100;

    Building() { super(getModels()); }

    private static List<Abstract3dModel> getModels() {
        //полная модель
        List<Abstract3dModel> models = new ArrayList<Abstract3dModel>();

        Union first = new Union(
                //передняя часть
                new Cube(new Dims3d(400, 265, 100)),
                new Cube(new Dims3d(200, 20, 100)).move(new Coords3d(0, 140, 0)),
                new Cube(new Dims3d(100, 20, 100)).move(new Coords3d(0, 160, 0)),
                //основное здание
                new Cube(new Dims3d(800, 265, 1000)).move(Coords3d.zOnly(-550)),
                //выступы
                new Cube(new Dims3d(840, 15, 1040)).move(new Coords3d(0, 110, -550)),
                new Cube(new Dims3d(440, 15, 140)).move(new Coords3d(0, 110, 0)),
                new Cube(new Dims3d(820, 10, 1020)).move(new Coords3d(0, 40, -550)),
                new Cube(new Dims3d(420, 10, 120)).move(new Coords3d(0, 40, 0)),
                new Cube(new Dims3d(820, 10, 1020)).move(new Coords3d(0, -130, -550)),
                new Cube(new Dims3d(420, 10, 120)).move(new Coords3d(0, -130, 0))
        );

        //вычетающиеся элементы, которые повторяются
        List<Abstract3dModel> differencingElements = new ArrayList<Abstract3dModel>();
        for(int i = 0; i < 5; i++) {
            //двери
            differencingElements.add(new Cube(new Dims3d(35, 55, 100)).move(new Coords3d(myCoords1[i], -105, 45)));
            //арки
            differencingElements.add(new Sphere(18).move(new Coords3d(myCoords1[i], 45, 55)));
            differencingElements.add(new Cube(new Dims3d(35, 115, 35)).move(new Coords3d(myCoords1[i], -10, 45)));
            //окна в арках
            differencingElements.add(new Cube(new Dims3d(30, 105, 100)).move(new Coords3d(myCoords1[i], -10, 45)));
        }
        for(int i = 0; i < 3; i++) {
            //боковые окна
            differencingElements.add(new Cube(new Dims3d(35, 40, 30)).move(new Coords3d(-250, myCoords2[i], -50)));
            differencingElements.add(new Cube(new Dims3d(35, 40, 30)).move(new Coords3d(250, myCoords2[i], -50)));
        }
        for(int i = 0; i < 13; i++) {
            //окна по левой стороне
            differencingElements.add(new Cube(new Dims3d(30, 40, 35)).move(new Coords3d(-400, -80, myCoords3)));
            differencingElements.add(new Cube(new Dims3d(30, 40, 35)).move(new Coords3d(-400, -20, myCoords3)));
            differencingElements.add(new Cube(new Dims3d(30, 40, 35)).move(new Coords3d(-400, 40, myCoords3)));
            //окна по правой стороне
            differencingElements.add(new Cube(new Dims3d(30, 40, 35)).move(new Coords3d(400, -80, myCoords3)));
            differencingElements.add(new Cube(new Dims3d(30, 40, 35)).move(new Coords3d(400, -20, myCoords3)));
            differencingElements.add(new Cube(new Dims3d(30, 40, 35)).move(new Coords3d(400, 40, myCoords3)));
            myCoords3 -= 75;
        }
        Difference second = new Difference(
                first,
                differencingElements
        );
        Difference third = new Difference(
                second,
                //углубление крыши
                new Cube(new Dims3d(760, 40, 940)).move(new Coords3d(0, 120, -550))
        );

        //добавляюшиеся элементы, которые повторяются.
        List<Abstract3dModel> anotherComponents = new ArrayList<Abstract3dModel>();
        for(int i = 0; i < 5; i++) {
            //перепонки окон
            anotherComponents.add(new Cube(new Dims3d(5, 145, 5)).move(new Coords3d(myCoords1[i], 0, 45)));
        }
        anotherComponents.add(new Cube(new Dims3d(400, 5, 5)).move(new Coords3d(0, 0, 45)));
        anotherComponents.add(new Cube(new Dims3d(400, 5, 5)).move(new Coords3d(0, -20, 45)));
        anotherComponents.add(third);
        Union fourth = new Union(
                anotherComponents
        );
        models.add(fourth);
        return models;
    }
}