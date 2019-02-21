import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, TextFormat, ICrudGetAction } from 'react-jhipster';
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
            <dd>
              <Translate contentKey={`jerkkApp.AuthStatusEnum.${jerkEntity.authStatus}`} />
            </dd>
            <dt>
              <Translate contentKey="jerkkApp.preference.wechat">Wechat</Translate>
            </dt>
            <dd>{jerkEntity.preference ? jerkEntity.preference.wechat : ''}</dd>
            <dt>
              <Translate contentKey="jerkkApp.jerk.jerkInfo">Registration Status</Translate>
            </dt>
            <dd>
              {jerkEntity.jerkInfo ? (
                jerkEntity.jerkInfo.fbzt === 1 ? (
                  <Link to={`/entity/registration/${jerkEntity.jerkInfo.id}`} className="btn btn-primary">
                    <Translate contentKey="jerkkApp.jerk.online">Published</Translate>
                  </Link>
                ) : (
                  <Link to={`/entity/registration/${jerkEntity.jerkInfo.id}`} className="btn btn-primary">
                    <Translate contentKey="jerkkApp.jerk.offline">Draft</Translate>
                  </Link>
                )
              ) : (
                <Link to={`/entity/registration/${jerkEntity.id}/new`} className="btn btn-primary">
                  <Translate contentKey="jerkkApp.jerk.nonregistration">No Registration</Translate>
                </Link>
              )}
            </dd>

            <dt>
              <span id="createdDate">
                <Translate contentKey="jerkkApp.jerk.createdDate">Created Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={jerkEntity.createdDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
            </dd>
            <dt>
              <span id="modifiedDate">
                <Translate contentKey="jerkkApp.jerk.modifiedDate">Modified Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={jerkEntity.modifiedDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
            </dd>
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
