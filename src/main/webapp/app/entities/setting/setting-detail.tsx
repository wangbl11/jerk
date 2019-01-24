import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, TextFormat, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './setting.reducer';
import { ISetting } from 'app/shared/model/setting.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISettingDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SettingDetail extends React.Component<ISettingDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { settingEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jerkkApp.setting.detail.title">Setting</Translate> [<b>{settingEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">
                <Translate contentKey="jerkkApp.setting.name">Name</Translate>
              </span>
            </dt>
            <dd>{settingEntity.name}</dd>
            <dt>
              <span id="type">
                <Translate contentKey="jerkkApp.setting.type">Type</Translate>
              </span>
            </dt>
            <dd>{settingEntity.type}</dd>
            <dt>
              <span id="value">
                <Translate contentKey="jerkkApp.setting.value">Value</Translate>
              </span>
            </dt>
            <dd>{settingEntity.value}</dd>
            <dt>
              <span id="defvalue">
                <Translate contentKey="jerkkApp.setting.defvalue">Defvalue</Translate>
              </span>
            </dt>
            <dd>{settingEntity.defvalue}</dd>
            <dt>
              <span id="createdDate">
                <Translate contentKey="jerkkApp.setting.createdDate">Created Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={settingEntity.createdDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
            </dd>
            <dt>
              <span id="modifiedDate">
                <Translate contentKey="jerkkApp.setting.modifiedDate">Modified Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={settingEntity.createdDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/setting" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/setting/${settingEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ setting }: IRootState) => ({
  settingEntity: setting.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SettingDetail);
