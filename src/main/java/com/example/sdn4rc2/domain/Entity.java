package com.example.sdn4rc2.domain;

import org.neo4j.ogm.annotation.GraphId;

public abstract class Entity {

  @GraphId
  public Long id;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || id == null || getClass() != o.getClass()) {
      return false;
    }

    Entity entity = (Entity) o;

    if (!id.equals(entity.id)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return (id == null) ? -1 : id.hashCode();
  }

}
