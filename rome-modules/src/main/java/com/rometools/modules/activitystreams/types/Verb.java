/*
 *  Copyright 2011 robert.cooper.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the icense for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package com.rometools.modules.activitystreams.types;

public enum Verb {
    /**
     * The "mark as favorite" Verb indicates that the Subject marked the Object as an item of
     * special interest. Objects so marked often appear in a collection of such Objects which MAY be
     * published as a feed of entries annotated with this Verb. If a particular service uses
     * favorites as a mechanism by which a user can recall items that were earlier marked, it MAY be
     * appropriate to also mark activities of this type with the "save" Verb as described in Section
     * 3.1.8. The verb URI for the "mark as favorite" Verb is
     * http://activitystrea.ms/schema/1.0/favorite.
     */
    MARK_AS_FAVORITE("http://activitystrea.ms/schema/1.0/favorite"),
    /**
     * The "start following" Verb indicates that the Subject began following the activity of the
     * Object. In most cases, the Object of this Verb will be a user, but it can potentially be of
     * any type that can sensibly generate activity.
     *
     * The verb URI for the "start following" Verb is http://activitystrea.ms/schema/1.0/follow.
     *
     * Processors MAY ignore (silently drop) successive identical "start following" activities
     * regardless of whether they maintain state sufficient to determine (A), (B), or (C) above.
     */
    START_FOLLOWING("http://activitystrea.ms/schema/1.0/follow"),
    /**
     * The "mark as liked" verb indicates that the actor indicated that it likes the object.
     *
     * The verb URI for the "mark as liked" Verb is http://activitystrea.ms/schema/1.0/like.
     */
    MARK_AS_LIKED("http://activitystrea.ms/schema/1.0/like"),
    /**
     * The "make friend" Verb indicates the creation of a friendship that is reciprocated by the
     * object.
     *
     * Since this verb implies an activity on the part of its object, processors MUST NOT accept
     * activities with this Verb unless they are able to verify through some external means that
     * there is in fact a reciprocated connection. For example, a processor MAY have received a
     * guarantee from a particular publisher that the publisher will only use this Verb in cases
     * where a reciprocal relationship exists.
     *
     * The verb URI for the "make friend" Verb is http://activitystrea.ms/schema/1.0/make-friend.
     */
    MAKE_FRIEND("http://activitystrea.ms/schema/1.0/make-friend"),
    /**
     * The "join" Verb indicates that the actor has become a member of the Object. This
     * specification only defines the meaning of this Verb when its Object is a Group, though
     * implementors SHOULD be prepared to handle other Object types as meaning MAY be provided by
     * extension specifications.
     *
     * Processors MAY ignore (silently drop) successive identical "join" activities regardless of
     * whether they maintain state sufficient to determine (A) or (B) above.
     *
     * The "join" Verb is identified by the URI http://activitystrea.ms/schema/1.0/join.
     */
    JOIN("http://activitystrea.ms/schema/1.0/join"),
    /**
     * The "play" verb indicates that the subject spent some time enjoying the object. For example,
     * if the object is a video this indicates that the subject watched all or part of the video.
     *
     * The "play" Verb is identified by the URI http://activitystrea.ms/schema/1.0/play.
     */
    PLAY("http://activitystrea.ms/schema/1.0/play"),
    /**
     * The "Post" Verb is described in section 8 of the AtomActivity specification. It is only
     * referenced here for completeness.
     *
     * http://activitystrea.ms/schema/1.0/post
     */
    POST("http://activitystrea.ms/schema/1.0/post"),
    /**
     * The "save" Verb indicates that the Subject has called out the Object as being of interest
     * primarily to him- or herself. Though this action MAY be shared publicly, the implication is
     * that the Object has been saved primarily for the actor's own benefit rather than to show it
     * to others as would be indicated by the "share" Verb (Section 3.1.9).
     *
     * The "save" Verb is identified by the URI http://activitystrea.ms/schema/1.0/save.
     */
    SAVE("http://activitystrea.ms/schema/1.0/save"),
    /**
     * The "share" Verb indicates that the Subject has called out the Object to readers. In most
     * cases, the actor did not create the Object being shared, but is instead drawing attention to
     * it.
     *
     * The "share" Verb is identified by the URI http://activitystrea.ms/schema/1.0/share.
     */
    SHARE("http://activitystrea.ms/schema/1.0/share"),
    /**
     * The "tag" verb indicates that the actor has identified the presence of a target inside
     * another object. For example, the actor may have specified that a particular user appears in a
     * photo.
     *
     * The "tag" verb is identified by the URI http://activitystrea.ms/schema/1.0/tag.
     *
     * The target of the "tag" verb gives the object in which the tag has been added. For example,
     * if a user appears in a photo, the activity:object is the user and the activity:target is the
     * photo.
     */
    TAG("http://activitystrea.ms/schema/1.0/tag"),
    /**
     * The "update" Verb indicates that the Subject has modified the referenced Object.
     *
     * Implementors SHOULD use verbs such as Section 3.1.7 where the Subject is adding new items to
     * a Section 3.2.8 or similar. Update is reserved for modifications to existing Objects or data
     * such as changing a user's profile information.
     *
     * The "update" Verb is identified by the URI http://activitystrea.ms/schema/1.0/update.
     */
    UPDATE("http://activitystrea.ms/schema/1.0/update"),
    /**
     *
     * <p>
     * The "positive RSVP" verb indicates that the actor has made a positive RSVP for the object.
     * This specification only defines the meaning of this verb when its object is an event (see <a
     * class='info' href='#event'>Section&nbsp;4.2.1<span> (</span><span
     * class='info'>Event</span><span>)</span></a>), though implementors SHOULD be prepared to
     * handle other object types as meaning MAY be provided by extension specifications.
     * </p>
     * <p>
     * The use of this Verb is only appropriate when the RSVP was created by an explicit action by
     * the actor. It is not appropriate to use this verb when a user has been added as an attendee
     * by an event organiser or administrator.
     * </p>
     * <p>
     * The verb URI for the "positive RSVP" Verb is
     * <tt>http://activitystrea.ms/schema/1.0/rsvp-yes</tt>.
     * </p>
     */
    RSVP_YES("http://activitystrea.ms/schema/1.0/rsvp-yes"),
    /**
     * <p>
     * The "possible RSVP" verb indicates that the actor has made a possible RSVP for the object.
     * This specification only defines the meaning of this verb when its object is an event (see <a
     * class='info' href='#event'>Section&nbsp;4.2.1<span> (</span><span
     * class='info'>Event</span><span>)</span></a>), though implementors SHOULD be prepared to
     * handle other object types as meaning MAY be provided by extension specifications.
     * </p>
     * <p>
     * The use of this Verb is only appropriate when the RSVP was created by an explicit action by
     * the actor. It is not appropriate to use this verb when a user has been added as an attendee
     * by an event organiser or administrator.
     * </p>
     * <p>
     * The verb URI for the "possible RSVP" Verb is
     * <tt>http://activitystrea.ms/schema/1.0/rsvp-maybe</tt>.
     * </p>
     */
    RSVP_MAYBE("http://activitystrea.ms/schema/1.0/rsvp-maybe"),
    /**
     * <p>
     * The "negative RSVP" verb indicates that the actor has made a negative RSVP for the object.
     * This specification only defines the meaning of this verb when its object is an event (see <a
     * class='info' href='#event'>Section&nbsp;4.2.1<span> (</span><span
     * class='info'>Event</span><span>)</span></a>), though implementors SHOULD be prepared to
     * handle other object types as meaning MAY be provided by extension specifications.
     * </p>
     * <p>
     * The use of this Verb is only appropriate when the RSVP was created by an explicit action by
     * the actor. It is not appropriate to use this verb when a user has been added as an attendee
     * by an event organiser or administrator.
     * </p>
     * <p>
     * The verb URI for the "negative RSVP" Verb is
     * <tt>http://activitystrea.ms/schema/1.0/rsvp-no</tt>.
     * </p>
     */
    RSVP_NO("http://activitystrea.ms/schema/1.0/rsvp-no");
    private final String iri;

    Verb(final String iri) {
        this.iri = iri;
    }

    @Override
    public String toString() {
        return iri;
    }

    public static Verb fromIRI(final String iri) {
        for (final Verb v : Verb.values()) {
            if (v.toString().equals(iri)) {
                return v;
            }
        }
        return null;
    }
}
