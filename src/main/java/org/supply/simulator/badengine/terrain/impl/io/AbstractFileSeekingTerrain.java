package org.supply.simulator.badengine.terrain.impl.io;

import org.supply.simulator.badengine.Partitions;
import org.supply.simulator.badengine.terrain.Terrain;
import org.supply.simulator.badengine.terrain.chunk.TerrainChunk;
import org.supply.simulator.core.dao.util.ArrayFloatByteTranslator;
import org.supply.simulator.logging.HasLogger;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.Iterator;

/**
 * Created by Brandon on 7/27/2014.
 */
public abstract class AbstractFileSeekingTerrain extends HasLogger implements Terrain {

    private ArrayFloatByteTranslator translator;
    private final RandomAccessFile file;

    {
        translator = new ArrayFloatByteTranslator();
    }

    protected AbstractFileSeekingTerrain() throws IOException {
        File temp = File.createTempFile("temp", ".bgf");
        temp.deleteOnExit(); //todo - maybe track this file if necessary and delete when finished
        file = new RandomAccessFile(temp, "rw");
    }

    protected AbstractFileSeekingTerrain(Path file) throws IOException {
        this.file = new RandomAccessFile(file.toAbsolutePath().toString(),"rw");
    }

    protected AbstractFileSeekingTerrain(File file) throws IOException {
        this.file = new RandomAccessFile(file,"rw");
    }

    class TerrainIterable implements Partitions<TerrainChunk> {
        @Override
        public Iterator<TerrainChunk> iterator() {
            return new TerrainIterator();
        }
    }

    protected void writeTerrainChunk(TerrainChunk chunk, int x, int y) throws IOException {
        int chunkRepSizeInBytes = getChunkRepSizeInBytes();
        int offset = (x*getChunkColumns()+y)*chunkRepSizeInBytes;
        long fileSizeNeeded = offset+chunkRepSizeInBytes;
        if(file.length()<fileSizeNeeded) {
            file.setLength(fileSizeNeeded);
        }
        synchronized (file) {
            file.seek(offset);
            file.write(chunk.getColors());
            file.seek(offset + getChunkColorRepSizeInBytes());
            file.write(translator.floatsToBytes(chunk.getPositions()));
        }
    }

    private TerrainChunk readTerrainChunk(int x, int y) throws IOException {
        int chunkRepSizeInBytes = getChunkRepSizeInBytes();
        int offset = (x*getChunkColumns()+y)*chunkRepSizeInBytes;
        byte[] colors;
        byte[] positions;
        synchronized (file) {
            file.seek(offset);
            colors = new byte[getChunkColorRepSizeInBytes()];
            positions = new byte[getChunkPositionRepSizeInBytes()];
            file.read(colors);
            file.seek(offset + getChunkColorRepSizeInBytes());
            file.read(positions);
        }
        return createTerrainChunk(translator.bytesToFloats(positions),colors);
    }


    protected TerrainChunk createTerrainChunk(float[] positions, byte[] colors) {
        TerrainChunk chunk = new TerrainChunk();
        chunk.setColors(colors);
        chunk.setPositions(positions);
        return chunk;
    }


    private class TerrainIterator implements Iterator<TerrainChunk> {
        private int i=0,j=0;

        TerrainIterator() {
            for(int i = 0; i<getChunkRows();i++) {
                for(int j = 0; j<getChunkColumns();j++) {
                    TerrainChunk chunk = getChunkData(getVertexRows(),getVertexColumns(),i*getVertexRows(),j*getVertexColumns());
                    try {
                        logger.info("writing terrain chunk for (" + i + "," + j + ")");
                        writeTerrainChunk(chunk, i, j);
                    } catch (IOException e) {
                        logger.info("Caught an exception while writing terrain chunk: ",e);
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            return i<getChunkRows() && j<getChunkColumns();
        }

        @Override
        public TerrainChunk next() {
            try {
                logger.info("reading terrain chunk for (" + i + "," + j + ")");
                TerrainChunk chunk = readTerrainChunk(i, j);
                j++;
                if(j == getChunkColumns()) {
                    j=0;
                    i++;
                }
                return chunk;
            } catch (IOException e) {
                logger.info("Caught an exception while reading terrain chunk: ",e);
            }
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("don't do that");
        }
    }

    @Override
    public final Partitions<TerrainChunk> partition() {
        return new TerrainIterable();
    }

    protected abstract int getChunkRepSizeInBytes();
    protected abstract int getChunkColorRepSizeInBytes();
    protected abstract int getChunkPositionRepSizeInBytes();
    protected abstract TerrainChunk getChunkData(int row, int col, int topLeftX, int topLeftY);
    protected abstract int getChunkRows();
    protected abstract int getChunkColumns();
    protected abstract int getVertexRows();
    protected abstract int getVertexColumns();
    protected abstract float getVertexDistanceHorizontal();
    protected abstract float getVertexDistanceVertical();
    protected abstract float getStartX();
    protected abstract float getStartY();
}
