import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './jerk.reducer';
import { IJerk } from 'app/shared/model/jerk.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IJerkDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class JerkDetail extends React.Component<IJerkDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { jerkEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jerkkApp.jerk.detail.title">Jerk</Translate> [<b>{jerkEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="username">
                <Translate contentKey="jerkkApp.jerk.username">Username</Translate>
              </span>
            </dt>
            <dd>{jerkEntity.username}</dd>
            <dt>
              <span id="passwd">
                <Translate contentKey="jerkkApp.jerk.passwd">Passwd</Translate>
              </span>
            </dt>
            <dd>{jerkEntity.passwd}</dd>
            <dt>
              <span id="displayname">
                <Translate contentKey="jerkkApp.jerk.displayname">Displayname</Translate>
              </span>
            </dt>
            <dd>{jerkEntity.displayname}</dd>
            <dt>
              <span id="authStatus">
                <Translate contentKey="jerkkApp.jerk.authStatus">Auth Status</Translate>
              </span>
            </dt>
            <dd>{jerkEntity.authStatus}</dd>
            <dt>
              <span id="createdDate">
                <Translate contentKey="jerkkApp.jerk.createdDate">Created Date</Translate>
              </span>
            </dt>
            <dd>{jerkEntity.createdDate}</dd>
            <dt>
              <span id="modifiedDate">
                <Translate contentKey="jerkkApp.jerk.modifiedDate">Modified Date</Translate>
              </span>
            </dt>
            <dd>{jerkEntity.modifiedDate}</dd>
            <dt>
              <Translate contentKey="jerkkApp.jerk.jerkInfo">Jerk Info</Translate>
            </dt>
            <dd>{jerkEntity.jerkInfo ? jerkEntity.jerkInfo.id : ''}</dd>
            <dt>
              <Translate contentKey="jerkkApp.jerk.preference">Preference</Translate>
            </dt>
            <dd>{jerkEntity.preference ? jerkEntity.preference.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/jerk" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/jerk/${jerkEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ jerk }: IRootState) => ({
  jerkEntity: jerk.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(JerkDetail);
