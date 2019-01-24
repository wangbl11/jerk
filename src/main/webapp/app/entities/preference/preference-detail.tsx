import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './preference.reducer';
import { IPreference } from 'app/shared/model/preference.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPreferenceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PreferenceDetail extends React.Component<IPreferenceDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { preferenceEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jerkkApp.preference.detail.title">Preference</Translate> [<b>{preferenceEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="wechat">
                <Translate contentKey="jerkkApp.preference.wechat">Wechat</Translate>
              </span>
            </dt>
            <dd>{preferenceEntity.wechat}</dd>
            <dt>
              <span id="address">
                <Translate contentKey="jerkkApp.preference.address">Address</Translate>
              </span>
            </dt>
            <dd>{preferenceEntity.address}</dd>
            <dt>
              <span id="imageUrl">
                <Translate contentKey="jerkkApp.preference.imageUrl">Image Url</Translate>
              </span>
            </dt>
            <dd>{preferenceEntity.imageUrl}</dd>
            <dt>
              <span id="lang">
                <Translate contentKey="jerkkApp.preference.lang">Lang</Translate>
              </span>
            </dt>
            <dd>{preferenceEntity.lang}</dd>
            <dt>
              <span id="createdDate">
                <Translate contentKey="jerkkApp.preference.createdDate">Created Date</Translate>
              </span>
            </dt>
            <dd>{preferenceEntity.createdDate}</dd>
            <dt>
              <span id="modifiedDate">
                <Translate contentKey="jerkkApp.preference.modifiedDate">Modified Date</Translate>
              </span>
            </dt>
            <dd>{preferenceEntity.modifiedDate}</dd>
          </dl>
          <Button tag={Link} to="/entity/preference" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/preference/${preferenceEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ preference }: IRootState) => ({
  preferenceEntity: preference.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PreferenceDetail);
