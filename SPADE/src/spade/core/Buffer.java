/*
--------------------------------------------------------------------------------
SPADE - Support for Provenance Auditing in Distributed Environments.
Copyright (C) 2011 SRI International

This program is free software: you can redistribute it and/or
modify it under the terms of the GNU General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
--------------------------------------------------------------------------------
 */
package spade.core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Buffer {

    // The buffer is essentially a queue to which vertices and edges are
    // added by the reporters and removed by the Kernel.
    private Queue<Object> queue;

    public Buffer() {
        queue = new ConcurrentLinkedQueue<Object>();
    }

    public boolean putVertex(AbstractVertex incomingVertex) {
        if (incomingVertex != null) {
            return queue.add(incomingVertex);
        } else {
            return false;
        }
    }

    public boolean putEdge(AbstractEdge incomingEdge) {
        if ((incomingEdge != null) && (incomingEdge.getSourceVertex() != null) && (incomingEdge.getDestinationVertex() != null)) {
            return queue.add(incomingEdge);
        } else {
            return false;
        }
    }

    public Object getBufferElement() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}