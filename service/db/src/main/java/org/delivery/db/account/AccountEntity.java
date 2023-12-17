package org.delivery.db.account;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@SuperBuilder
@Table(name = "account")
@EqualsAndHashCode(callSuper = true)
public class AccountEntity extends BaseEntity {


}
