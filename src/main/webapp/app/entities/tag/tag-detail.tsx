import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, TextFormat, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tag.reducer';
import { ITag } from 'app/shared/model/tag.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITagDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TagDetail extends React.Component<ITagDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { tagEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jerkkApp.tag.detail.title">Tag</Translate> [<b>{tagEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">
                <Translate contentKey="jerkkApp.tag.name">Name</Translate>
              </span>
            </dt>
            <dd>{tagEntity.name}</dd>
            <dt>
              <span id="type">
                <Translate contentKey="jerkkApp.tag.type">Type</Translate>
              </span>
            </dt>
            <dd>{tagEntity.type}</dd>
            <dt>
              <span id="status">
                <Translate contentKey="jerkkApp.tag.status">Status</Translate>
              </span>
            </dt>
            <dd>{tagEntity.status}</dd>
            <dt>
              <span id="weight">
                <Translate contentKey="jerkkApp.tag.weight">Weight</Translate>
              </span>
            </dt>
            <dd>{tagEntity.weight}</dd>
            <dt>
              <span id="createdDate">
                <Translate contentKey="jerkkApp.tag.createdDate">Created Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={tagEntity.createdDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
            </dd>
            <dt>
              <span id="modifiedDate">
                <Translate contentKey="jerkkApp.tag.modifiedDate">Modified Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={tagEntity.modifiedDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/tag" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/tag/${tagEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ tag }: IRootState) => ({
  tagEntity: tag.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TagDetail);
