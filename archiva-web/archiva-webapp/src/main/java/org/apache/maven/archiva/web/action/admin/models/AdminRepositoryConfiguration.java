package org.apache.maven.archiva.web.action.admin.models;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.commons.lang.StringUtils;
import org.apache.maven.archiva.common.utils.PathUtil;
import org.apache.maven.archiva.configuration.RepositoryConfiguration;
import org.apache.maven.archiva.model.RepositoryContentStatistics;
import org.apache.maven.archiva.model.RepositoryURL;

import java.io.File;

/**
 * AdminRepositoryConfiguration 
 *
 * @author <a href="mailto:joakim@erdfelt.com">Joakim Erdfelt</a>
 * @version $Id$
 */
public class AdminRepositoryConfiguration
    extends RepositoryConfiguration
{
    private String directory;

    private RepositoryContentStatistics stats;

    public AdminRepositoryConfiguration()
    {
        super();
    }

    /**
     * Copy Constructor.
     */
    public AdminRepositoryConfiguration( RepositoryConfiguration repoconfig )
    {
        this.setId( repoconfig.getId() );
        this.setName( repoconfig.getName() );
        this.setUrl( repoconfig.getUrl() );
        this.setLayout( repoconfig.getLayout() );
        this.setIndexed( repoconfig.isIndexed() );
        this.setReleases( repoconfig.isReleases() );
        this.setSnapshots( repoconfig.isSnapshots() );

        this.setIndexDir( repoconfig.getIndexDir() );
        this.setRefreshCronExpression( repoconfig.getRefreshCronExpression() );

        if ( repoconfig.isManaged() )
        {
            RepositoryURL url = new RepositoryURL( repoconfig.getUrl() );
            this.setDirectory( url.getPath() );
        }
    }

    public boolean isDirectoryExists()
    {
        if ( StringUtils.isBlank( directory ) )
        {
            return false;
        }

        File dir = new File( directory );
        return ( dir.exists() && dir.isDirectory() );
    }

    public String getDirectory()
    {
        return directory;
    }

    public void setDirectory( String directory )
    {
        this.directory = directory;
        this.setUrl( PathUtil.toUrl( directory ) );
    }

    public RepositoryContentStatistics getStats()
    {
        return stats;
    }

    public void setStats( RepositoryContentStatistics stats )
    {
        this.stats = stats;
    }
}
